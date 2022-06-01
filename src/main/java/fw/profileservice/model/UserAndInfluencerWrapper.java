package fw.profileservice.model;

import lombok.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserAndInfluencerWrapper {

    private User user;
    private Influencer influencer;

}
