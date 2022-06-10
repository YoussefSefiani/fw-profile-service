package fw.profileservice.model;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;



@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private Long userId;
    private UserType userType;

}
