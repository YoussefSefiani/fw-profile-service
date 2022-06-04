package fw.profileservice.service;

import fw.profileservice.feign.UserRestConsumer;
import fw.profileservice.model.*;
import fw.profileservice.repository.BrandRepository;
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

    @Autowired
    public BrandService(BrandRepository brandRepository, UserRestConsumer userRestConsumer){
        this.brandRepository = brandRepository;
        this.userRestConsumer = userRestConsumer;
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

        Brand brand = brandRepository.findBrandByUserIdBrand(userId)
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
    public void updateBrand(Long brandId, Brand newBrand) {
        Brand brand = brandRepository.findById(brandId)
                .orElseThrow(() -> new IllegalStateException(
                        "brand with id " + brandId + "does not exist"
                ));

//        String description = newInfluencer.getDescription();
//        String headTitle = newInfluencer.getHeadTitle();
//        String ibanNumber = newInfluencer.getIbanNumber();
//        String offers = newInfluencer.getOffers();
//        String partnerships = newInfluencer.getPartnerships();
//
//        if (description != null && description.length() > 0 && !Objects.equals(influencer.getDescription(), description))
//            influencer.setDescription(description);
//
//        if (headTitle != null && headTitle.length() > 0 && !Objects.equals(influencer.getHeadTitle(), headTitle))
//            influencer.setDescription(description);
//
//        if (ibanNumber != null && ibanNumber.length() > 0 && !Objects.equals(influencer.getIbanNumber(), ibanNumber))
//            influencer.setDescription(description);
//
//        if (offers != null && offers.length() > 0 && !Objects.equals(influencer.getOffers(), offers))
//            influencer.setDescription(description);
//
//        if (partnerships != null && partnerships.length() > 0 && !Objects.equals(influencer.getPartnerships(), partnerships))
//            influencer.setDescription(description);


    }
}
