package fw.profileservice.service;

import fw.profileservice.feign.UserRestConsumer;
import fw.profileservice.model.*;
import fw.profileservice.repository.BrandRepository;
import fw.profileservice.repository.InfluencerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            Optional<Influencer> influencer = influencerRepository.findInfluencerByUserIdInfluencer(userId);
            if (influencer.isPresent()) {
                return new UserAndInfluencerWrapper(user, influencer.get());
            }
        } else {
            Optional<Brand> brand = brandRepository.findBrandByUserIdBrand(userId);
            if (brand.isPresent()) {
                return new UserAndBrandWrapper(user, brand.get());
            }
        }
        return null;
    }


    public void updateInfluencerProfile(Long userId, String token, UserAndInfluencerWrapper newData) {
        influencerService.updateInfluencer(userId, newData.getInfluencer());
        userRestConsumer.updateUser(userId, token, newData.getUser());
    }

}
