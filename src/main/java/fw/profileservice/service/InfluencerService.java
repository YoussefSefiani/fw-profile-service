package fw.profileservice.service;


import fw.profileservice.feign.UserRestConsumer;
import fw.profileservice.model.*;
import fw.profileservice.repository.InfluencerRepository;

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
    private final UserRestConsumer userRestConsumer;

    @Autowired
    public InfluencerService(InfluencerRepository influencerRepository, UserRestConsumer userRestConsumer) {
        this.influencerRepository = influencerRepository;
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
        Influencer influencer = influencerRepository.findInfluencerByUserIdInfluencer(userId)
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
                  /*  (Long) null, */
                    registerRequest.getUserId()
            );
            influencerRepository.save(influencer);
        } catch (Exception e) {
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
        Influencer influencer = influencerRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Influencer with id %s does not exist", userId))
                );

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

    public boolean checkFirstTimeOnProfile(Long userId) {
        Influencer influencer = influencerRepository.findInfluencerByUserIdInfluencer(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Influencer with id %s does not exist", userId))
                );
        return influencer.getIbanNumber() == null;
    }

    public void confirmProfile(Long userId, Influencer influencerProfileData) {
        Influencer influencer = influencerRepository.findInfluencerByUserIdInfluencer(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Influencer with id %s does not exist", userId))
                );

        influencer.setIbanNumber(influencerProfileData.getIbanNumber());
        influencer.setSocialMedia(influencerProfileData.getSocialMedia());
        influencer.setHeadTitle(influencerProfileData.getHeadTitle());
        influencer.setDescription(influencerProfileData.getDescription());
        influencer.setLanguages(influencerProfileData.getLanguages());
        influencer.setCountries(influencerProfileData.getCountries());
        influencer.setSectors(influencerProfileData.getSectors());
        influencer.setStoryPrice(influencerProfileData.getStoryPrice());
        influencer.setPostPrice(influencerProfileData.getPostPrice());
        influencer.setHighlightPrice(influencerProfileData.getHighlightPrice());
        influencer.setAddress(influencerProfileData.getAddress());
        influencer.setCity(influencerProfileData.getCity());
        influencer.setPostalCode(influencerProfileData.getPostalCode());

        influencerRepository.save(influencer);
    }
}
