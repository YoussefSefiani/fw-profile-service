package fw.profileservice.service;


import fw.profileservice.model.*;
import fw.profileservice.repository.InfluencerRepository;
import org.hibernate.usertype.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class InfluencerService {

    private final InfluencerRepository influencerRepository;


    @Autowired
    public InfluencerService(InfluencerRepository influencerRepository) {
        this.influencerRepository = influencerRepository;
    }

    public List<Influencer> getInfluencers() {
        return influencerRepository.findAll();
    }

    public Influencer getInfluencer(Long influencerId) {

        return influencerRepository.findById(influencerId)
                .orElseThrow(() -> new IllegalStateException(
                        "influencer with id " + influencerId + " does not exist"
                ));
    }

    public void registerInfluencer(RegisterRequest registerRequest) {
        System.out.println("USER ID " + registerRequest.getUserId());
        try {
            System.out.println("USER ID " + registerRequest.getUserId());
            Influencer influencer = new Influencer(
                    (Long) null,
                    registerRequest.getUserId()
            );
            influencerRepository.save(influencer);
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    public void deleteInfluencer(Long influencerId) {
        boolean exists = influencerRepository.existsById(influencerId);

        if (!exists) {
            throw new IllegalStateException("influencer with id " + influencerId + " does not exists");
        }
        influencerRepository.deleteById(influencerId);
    }

    @Transactional
    public void updateInfluencer(Long influencerId, Influencer newInfluencer) {
        Influencer influencer = influencerRepository.findById(influencerId)
                .orElseThrow(() -> new IllegalStateException(
                        "influencer with id " + influencerId + "does not exist"
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
