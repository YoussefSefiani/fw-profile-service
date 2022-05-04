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
public class Brand {

    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private Long id;

    private Long userId;

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

    private String offers;

    private String partnerships;

}
