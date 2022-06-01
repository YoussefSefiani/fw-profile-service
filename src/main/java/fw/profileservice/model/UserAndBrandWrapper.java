package fw.profileservice.model;

import lombok.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserAndBrandWrapper {

    private User user;
    private Brand brand;

}