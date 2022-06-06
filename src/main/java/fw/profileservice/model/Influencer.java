package fw.profileservice.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Influencer {

    public Influencer(Long userIdInfluencer) {
        this.userIdInfluencer = userIdInfluencer;
    }

    @Id
    @Column(name = "user_id")
    private Long userIdInfluencer;

    private String ibanNumber;

    @JoinColumn(name = "user_id_influencer")
    @OneToMany(cascade = CascadeType.ALL)
    private List<SocialMedia> socialMedia;

    private String headTitle;

    private String description;

    @JoinColumn(name = "user_id_influencer")
    @OneToMany(cascade = CascadeType.ALL)
    private List<Language> languages;

    @JoinColumn(name = "user_id_influencer")
    @OneToMany(cascade = CascadeType.ALL)
    private List<Country> countries;

    @JoinColumn(name = "user_id_influencer")
    @OneToMany(cascade = CascadeType.ALL)
    private List<Sector> sectors;

    private int storyPrice;
    private int postPrice;
    private int highlightPrice;

    public Influencer(RegisterRequest registerRequest, Long userId) {
    }

    public void updateInfluencer(Influencer influencer) {
        this.ibanNumber = influencer.getIbanNumber();
        this.socialMedia = influencer.getSocialMedia();
        this.headTitle = influencer.getHeadTitle();
        this.description = influencer.getDescription();
        this.languages = influencer.getLanguages();
        this.sectors = influencer.getSectors();
        this.storyPrice = influencer.getStoryPrice();
        this.postPrice = influencer.getPostPrice();
        this.highlightPrice = influencer.getHighlightPrice();
        this.socialMedia = influencer.getSocialMedia();
        this.countries = influencer.getCountries();
    }

}
