package fw.profileservice.controller;

import fw.profileservice.model.Influencer;
import fw.profileservice.model.UserAndInfluencerWrapper;
import fw.profileservice.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/profile")
public class ProfileController {


    private final ProfileService profileService;

    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }


    @GetMapping(path = "{userId}")
    public Object getProfile(@PathVariable("userId") Long userId, @RequestHeader("Authorization") String token) {
        return profileService.getProfile(userId, token);
    }

    @PutMapping(path = "influencer/{userId}")
    public void updateInfluencerProfile(@PathVariable("userId") Long userId, @RequestHeader("Authorization") String token, @RequestBody UserAndInfluencerWrapper newData) {
        profileService.updateInfluencerProfile(userId, token, newData);
    }
}
