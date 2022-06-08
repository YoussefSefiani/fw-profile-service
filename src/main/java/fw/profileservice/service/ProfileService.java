package fw.profileservice.service;

import fw.profileservice.feign.UserRestConsumer;
import fw.profileservice.model.*;
import fw.profileservice.repository.BrandRepository;
import fw.profileservice.repository.InfluencerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class ProfileService {

    private final UserRestConsumer userRestConsumer;
    private final InfluencerRepository influencerRepository;
    private final BrandRepository brandRepository;
    private final BrandService brandService;
    private final InfluencerService influencerService;

    @Autowired
    public ProfileService(UserRestConsumer userRestConsumer, InfluencerRepository influencerRepository, BrandRepository brandRepository, BrandService brandService, InfluencerService influencerService) {
        this.userRestConsumer = userRestConsumer;
        this.influencerRepository = influencerRepository;
        this.brandRepository = brandRepository;
        this.brandService = brandService;
        this.influencerService = influencerService;
    }

    public Object getProfile(Long userId, String token) {
        User user = userRestConsumer.getUser(userId, token);

        if (user.getUserType().equals(UserType.INFLUENCER)) {
            Optional<Influencer> influencer = influencerRepository.findByUserIdInfluencer(userId);
            if (influencer.isPresent()) {
                return new UserAndInfluencerWrapper(user, influencer.get());
            }
        } else {
            Optional<Brand> brand = brandRepository.findByUserIdBrand(userId);
            if (brand.isPresent()) {
                return new UserAndBrandWrapper(user, brand.get());
            }
        }
        return null;
    }

    public boolean checkFirstTimeOnProfileInfluencer(Long userId) {
        Influencer influencer = influencerRepository.findByUserIdInfluencer(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Influencer with id %s does not exist", userId))
                );
        return influencer.getIbanNumber() == null;
    }


    public void updateInfluencerProfile(Long userId, String token, UserAndInfluencerWrapper newData) {
        influencerService.updateInfluencer(userId, newData.getInfluencer());
        userRestConsumer.updateUser(userId, token, newData.getUser());
    }

    public void updateBrandProfile(Long userId, String token, UserAndBrandWrapper newData) {
        brandService.updateBrand(userId, newData.getBrand());
        userRestConsumer.updateUser(userId, token, newData.getUser());
    }
}
