package fw.profileservice.controller;

import fw.profileservice.model.Influencer;
import fw.profileservice.model.RegisterRequest;
import fw.profileservice.model.UserAndInfluencerWrapper;
import fw.profileservice.service.InfluencerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/influencer")
public class InfluencerController {

    private final InfluencerService influencerService;

    @Autowired
    public InfluencerController(InfluencerService influencerService) {
        this.influencerService = influencerService;
    }


    @GetMapping
    public List<UserAndInfluencerWrapper> getInfluencers(@RequestHeader("Authorization") String token) {
        return influencerService.getInfluencers(token);
    }

    @GetMapping(path = "{userId}")
    public UserAndInfluencerWrapper getInfluencer(@PathVariable("userId") Long userId, @RequestHeader("Authorization") String token) {
        return influencerService.getInfluencerByUserId(userId, token);
    }

    @PostMapping
    public void registerInfluencer(@RequestBody RegisterRequest registerRequest) {
        influencerService.registerInfluencer(registerRequest);
    }

    @DeleteMapping(path = "{userId}")
    public void deleteInfluencer(@PathVariable("userId") Long userId) {
        influencerService.deleteInfluencer(userId);
    }

    @PutMapping(path = "{userId}")
    public void updateInfluencer(@PathVariable("userId") Long userId, @RequestBody Influencer influencer) {
        influencerService.updateInfluencer(userId, influencer);
    }

    @PutMapping(path = "profile/confirm/{userId}")
    public void confirmProfile(@PathVariable("userId") Long userId, @RequestBody Influencer influencer) {
        influencerService.confirmProfile(userId, influencer);
    }
}
