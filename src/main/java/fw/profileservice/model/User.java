package fw.profileservice.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private Long id;

    public User(String address, String city, int postalCode, CountryList country) {
        this.address = address;
        this.city = city;
        this.postalCode = postalCode;
        this.country = country;
    }

    public void updateUser(User user) {
        this.address = user.getAddress();
        this.city = user.getCity();
        this.postalCode = user.getPostalCode();
        this.country = user.getCountry();
    }

    @NotBlank(message = "firstname must be not empty")
    private String firstName;

    @NotBlank(message = "lastname must be not empty")
    private String lastName;

    // @Column(unique = true)
    @NotBlank(message = "username must be not empty")
    private String userName;

    @NotBlank(message = "password must be not empty")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    // @Column(unique = true)
    @NotBlank(message = "email must be not empty")
    @Email
    private String email;

    // @Column(unique = true)
    private String phoneNumber;
    private String address;
    private String city;
    private int postalCode;
    private CountryList country;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "yyyy-MM-dd")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date birthdate;

    private int rating;
    private UserType userType;
    private boolean active;
    private String roles;

}
