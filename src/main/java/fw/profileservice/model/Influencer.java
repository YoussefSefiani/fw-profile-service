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

    public Influencer(Long id, Long userId) {
        this.id = id;
        this.userId = userId;
    }

    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private Long id;

    private Long userId;

    private String ibanNumber;

    @JoinColumn(name = "user_id")
    @OneToMany(cascade = CascadeType.ALL)
    private List<SocialMedia> socialMedia;

    private String headTitle;

    private String description;

    @JoinColumn(name = "user_id")
    @OneToMany(cascade = CascadeType.ALL)
    private List<Language> languages;

    @JoinColumn(name = "user_id")
    @OneToMany(cascade = CascadeType.ALL)
    private List<Country> countries;

    @JoinColumn(name = "user_id")
    @OneToMany(cascade = CascadeType.ALL)
    private List<Sector> sectors;

    private int storyPrice;
    private int postPrice;
    private int highlightPrice;

    private String Address;
    private String City;
    private int postalCode;

    public Influencer(RegisterRequest registerRequest, Long userId) {
    }
}
