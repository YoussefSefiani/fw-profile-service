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

    @Autowired
    public ProfileService(UserRestConsumer userRestConsumer, InfluencerRepository influencerRepository, BrandRepository brandRepository) {
        this.userRestConsumer = userRestConsumer;
        this.influencerRepository = influencerRepository;
        this.brandRepository = brandRepository;
    }

    public Object getProfile(Long userId, String token) {
        User user = userRestConsumer.getUser(userId, token);

        if(user.getUserType().equals(UserType.INFLUENCER)) {
            Optional<Influencer> influencer = influencerRepository.findInfluencerByUserId(userId);
            if(influencer.isPresent()) {
                return new UserAndInfluencerWrapper(user, influencer.get());
            }
        } else {
            Optional<Brand> brand = brandRepository.findBrandByUserId(userId);
            if(brand.isPresent()) {
                return new UserAndBrandWrapper(user, brand.get());
            }
        }

        return null;
    }


}
