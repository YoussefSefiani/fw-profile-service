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

    public Brand(/*Long id,*/ Long userIdBrand) {
      //  this.id = id;
        this.userIdBrand = userIdBrand;
    }

//    @Id
//    @GeneratedValue(
//            strategy = GenerationType.AUTO
//    )
    //private Long id;

    @Id
    @Column(name = "user_id")
    private Long userIdBrand;

    @JoinColumn(name = "user_id_brand")
    @OneToMany(cascade = CascadeType.ALL)
    private List<SocialMedia> socialMedia;

    private String headTitle;

    private String description;

    @JoinColumn(name = "user_id_brand")
    @OneToMany(cascade = CascadeType.ALL)
    private List<Language> languages;

    @JoinColumn(name = "user_id_brand")
    @OneToMany(cascade = CascadeType.ALL)
    private List<Country> countries;

    @JoinColumn(name = "user_id_brand")
    @OneToMany(cascade = CascadeType.ALL)
    private List<Sector> sectors;

    public Brand(RegisterRequest registerRequest, Long userId) {
    }

}
