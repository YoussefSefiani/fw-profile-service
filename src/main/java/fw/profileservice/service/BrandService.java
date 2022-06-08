package fw.profileservice.service;

import fw.profileservice.feign.UserRestConsumer;
import fw.profileservice.model.*;
import fw.profileservice.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BrandService {

    private final BrandRepository brandRepository;
    private final UserRestConsumer userRestConsumer;
    private final CountryRepository countryRepository;
    private final LanguageRepository languageRepository;
    private final SectorRepository sectorRepository;
    private final SocialMediaRepository socialMediaRepository;

    @Autowired
    public BrandService(BrandRepository brandRepository, UserRestConsumer userRestConsumer, CountryRepository countryRepository, LanguageRepository languageRepository, SectorRepository sectorRepository, SocialMediaRepository socialMediaRepository){
        this.brandRepository = brandRepository;
        this.userRestConsumer = userRestConsumer;
        this.countryRepository = countryRepository;
        this.languageRepository = languageRepository;
        this.sectorRepository = sectorRepository;
        this.socialMediaRepository = socialMediaRepository;
    }

    public List<UserAndBrandWrapper> getBrands(String token) {
        List<Brand> brands = brandRepository.findAll();
        List<User> users = userRestConsumer.getUsers(token);
        List<UserAndBrandWrapper> mergedList = new ArrayList<>();
        users.forEach(user -> {
            Optional<Brand> brandOptional = brands.stream().filter(e -> e.getUserIdBrand().equals(user.getId())).findFirst();
            brandOptional.ifPresent(brand -> mergedList.add(new UserAndBrandWrapper(user, brand)));
        });
        System.out.println(mergedList);
        return mergedList;
    }

    public UserAndBrandWrapper getBrandByUserId(Long userId, String token) {

        Brand brand = brandRepository.findByUserIdBrand(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Brand with id %s does not exist", userId))
                );

        User user = userRestConsumer.getUser(userId, token);
        return new UserAndBrandWrapper(user, brand);
    }

    public void registerBrand(RegisterRequest registerRequest) {
        try {
            System.out.println("USER ID " + registerRequest.getUserId());
            Brand brand = new Brand(
                    registerRequest.getUserId()
            );
            brandRepository.save(brand);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Error during register request" );
        }
    }

    public void deleteBrand(Long userId) {
        boolean exists = brandRepository.existsByUserIdBrand(userId);

        if (!exists) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format("Brand with id %s does not exist", userId));
        }
        brandRepository.deleteByUserIdBrand(userId);
    }

    @Transactional
    public void updateBrand(Long userId, Brand newBrand) {
        Brand brand = brandRepository.findByUserIdBrand(userId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Brand with id %s does not exist", userId))
                );

        clearExistingProfileData(userId);
        brand.updateBrand(newBrand);
        brandRepository.save(brand);

    }

    public void clearExistingProfileData(Long userId) {
        socialMediaRepository.deleteByUserIdBrand(userId);
        sectorRepository.deleteByUserIdBrand(userId);
        languageRepository.deleteByUserIdBrand(userId);
        countryRepository.deleteByUserIdBrand(userId);
    }
}
