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
public class InfluencerService {

    private final InfluencerRepository influencerRepository;
    private final CountryRepository countryRepository;
    private final LanguageRepository languageRepository;
    private final SectorRepository sectorRepository;
    private final SocialMediaRepository socialMediaRepository;
    private final UserRestConsumer userRestConsumer;

    @Autowired
    public InfluencerService(InfluencerRepository influencerRepository, CountryRepository countryRepository,
                             LanguageRepository languageRepository, SectorRepository sectorRepository,
                             SocialMediaRepository socialMediaRepository, UserRestConsumer userRestConsumer) {
        this.influencerRepository = influencerRepository;
        this.countryRepository = countryRepository;
        this.languageRepository = languageRepository;
        this.sectorRepository = sectorRepository;
        this.socialMediaRepository = socialMediaRepository;
        this.userRestConsumer = userRestConsumer;
    }

    public List<UserAndInfluencerWrapper> getInfluencers(String token) {
        List<Influencer> influencers = influencerRepository.findAll();
        List<User> users = userRestConsumer.getUsers(token);
        List<UserAndInfluencerWrapper> mergedList = new ArrayList<>();
        users.forEach(user -> {
            Optional<Influencer> influencerOptional = influencers.stream().filter(e -> e.getUserIdInfluencer().equals(user.getId())).findFirst();
            influencerOptional.ifPresent(influencer -> mergedList.add(new UserAndInfluencerWrapper(user, influencer)));
        });
        System.out.println(mergedList);
        return mergedList;
    }

    public UserAndInfluencerWrapper getInfluencerByUserId(Long userId, String token) {
        Influencer influencer = influencerRepository.findByUserIdInfluencer(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("User with id %s does not exist", userId))
                );
        User user = userRestConsumer.getUser(userId, token);
        return new UserAndInfluencerWrapper(user, influencer);
    }

    public void registerInfluencer(RegisterRequest registerRequest) {
        try {
            System.out.println("USER ID " + registerRequest.getUserId());
            Influencer influencer = new Influencer(
                    registerRequest.getUserId()
            );
            influencerRepository.save(influencer);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Error during register request" );
        }
    }

    public void deleteInfluencer(Long userId) {
        boolean exists = influencerRepository.existsByUserIdInfluencer(userId);

        if (!exists) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format("Influencer with id %s does not exist", userId));
        }
        influencerRepository.deleteByUserIdInfluencer(userId);
    }

    @Transactional
    public void updateInfluencer(Long userId, Influencer newInfluencer) {
        Influencer influencer = influencerRepository.findByUserIdInfluencer(userId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Influencer with id %s does not exist", userId))
                );

        clearExistingProfileData(userId);
        influencer.updateInfluencer(newInfluencer);
        influencerRepository.save(influencer);
    }

    public void confirmProfile(Long userId, Influencer influencerProfileData) {
        Influencer influencer = influencerRepository.findByUserIdInfluencer(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Influencer with id %s does not exist", userId))
                );
        influencer.updateInfluencer(influencerProfileData);
        influencerRepository.save(influencer);
    }

    public void clearExistingProfileData(Long userId) {
        socialMediaRepository.deleteByUserIdInfluencer(userId);
        sectorRepository.deleteByUserIdInfluencer(userId);
        languageRepository.deleteByUserIdInfluencer(userId);
        countryRepository.deleteByUserIdInfluencer(userId);
    }
}
