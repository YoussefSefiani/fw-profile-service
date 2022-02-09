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

    public void registerDummyInfluencer(RegisterRequest registerRequest) {

        System.out.println("In register influencer service function");
        System.out.printf("Influencer data %s", registerRequest);

        LocalDate dummyDate = LocalDate.of(2000,02,04);
        List<SocialMedia> dummySocialMedia = List.of(
                new SocialMedia(null, SocialMediaList.SNAPCHAT.name(), "testlink"),
                new SocialMedia(null, SocialMediaList.INSTAGRAM.name(), "testlink2")
        );

        List<Language> dummyLanguages =  List.of(
                new Language(null, LanguageList.DUTCH.name()),
                new Language(null, LanguageList.ENGLISH.name())
        );

        List<Country> dummyCountries = List.of(
                new Country(null, CountryList.BELGIUM.name()),
                new Country(null, CountryList.FRANCE.name())
        );

        List<Sector> dummySector = List.of(
                new Sector(null, SectorList.IT.name()),
                new Sector(null, SectorList.FINANCE.name())
        );


        Influencer dummyInfluencer = new Influencer(
                null,
                    registerRequest.getUserId(), //TO DO: change parameter and give userID with in link as parameter
                "BE123456742546",
                dummySocialMedia,
                "I am an influencer",
                "this is the description",
                dummyLanguages,
                dummyCountries,
                dummySector,
                "here offers",
                "here partnerships"
        );

        //if (userService.checkUsername(dummyInfluencer.getUser()))
            influencerRepository.save(dummyInfluencer);

    }

    public void registerInfluencer(RegisterRequest registerRequest) {
//        Optional<Influencer> influencerOptional = influencerRepository.findInfluencerByUsername(influencer.getUser().getUsername());
//
//        if (influencerOptional.isPresent())
//            influencerRepository.save(influencer);

       // if (userService.checkUsername(influencer.getUser()))

       // influencerRepository.save(influencer);

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

        //TODO: add more elements to update
        String description = newInfluencer.getDescription();
        String headTitle = newInfluencer.getHeadTitle();
        String ibanNumber = newInfluencer.getIbanNumber();
        String offers = newInfluencer.getOffers();
        String partnerships = newInfluencer.getPartnerships();

        if (description != null && description.length() > 0 && !Objects.equals(influencer.getDescription(), description))
            influencer.setDescription(description);

        if (headTitle != null && headTitle.length() > 0 && !Objects.equals(influencer.getHeadTitle(), headTitle))
            influencer.setDescription(description);

        if (ibanNumber != null && ibanNumber.length() > 0 && !Objects.equals(influencer.getIbanNumber(), ibanNumber))
            influencer.setDescription(description);

        if (offers != null && offers.length() > 0 && !Objects.equals(influencer.getOffers(), offers))
            influencer.setDescription(description);

        if (partnerships != null && partnerships.length() > 0 && !Objects.equals(influencer.getPartnerships(), partnerships))
            influencer.setDescription(description);

        //TODO: check if not necessary to add repository

    }

}
