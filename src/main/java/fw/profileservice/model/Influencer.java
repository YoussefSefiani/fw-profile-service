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

    public Influencer(/*Long id,*/ Long userIdInfluencer) {
    //    this.id = id;
        this.userIdInfluencer = userIdInfluencer;
    }

//    @Id
//    @GeneratedValue(
//            strategy = GenerationType.AUTO
//    )
//    private Long id;

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

    private String address;
    private String city;
    private int postalCode;

    public Influencer(RegisterRequest registerRequest, Long userId) {
    }

    public Influencer(String ibanNumber, List<SocialMedia> socialMedia, String headTitle, String description, List<Language> languages, List<Country> countries, List<Sector> sectors, int storyPrice, int postPrice, int highlightPrice, String address, String city, int postalCode) {
        this.ibanNumber = ibanNumber;
        this.socialMedia = socialMedia;
        this.headTitle = headTitle;
        this.description = description;
        this.languages = languages;
        this.countries = countries;
        this.sectors = sectors;
        this.storyPrice = storyPrice;
        this.postPrice = postPrice;
        this.highlightPrice = highlightPrice;
        this.address = address;
        this.city = city;
        this.postalCode = postalCode;
    }
}
