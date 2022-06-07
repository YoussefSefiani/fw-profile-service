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

    public Brand(Long userIdBrand) {
        this.userIdBrand = userIdBrand;
    }

    @Id
    @Column(name = "user_id")
    private Long userIdBrand;

    @JoinColumn(name = "user_id_brand")
    @OneToMany(cascade = CascadeType.ALL)
    private List<SocialMedia> socialMedia;

    private String headTitle;

    @Column(length = 2000)
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
