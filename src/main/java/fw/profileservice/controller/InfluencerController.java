package fw.profileservice.controller;

import fw.profileservice.model.Influencer;
import fw.profileservice.model.RegisterRequest;
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
    public List<Influencer> getInfluencers() {
        return influencerService.getInfluencers();
    }

    @GetMapping(path = "{influencerId}")
    public Influencer getInfluencer(@PathVariable("influencerId") Long influencerId) {
        return influencerService.getInfluencer(influencerId);
    }

    @PostMapping(path = "dummy")
    public void registerDummyInfluencer(@RequestBody RegisterRequest registerRequest) {
        System.out.println("in registerDummyInfluencer function controller");
        influencerService.registerDummyInfluencer(registerRequest);
    }

    @PostMapping
    public void registerInfluencer(@RequestBody RegisterRequest registerRequest) {
        System.out.println("in registerInfluencer function controller");
        influencerService.registerInfluencer(registerRequest);
    }

    @DeleteMapping(path = "{influencerId}")
    public void deleteInfluencer(@PathVariable("influencerId") Long influencerId) {
        influencerService.deleteInfluencer(influencerId);
    }

    @PutMapping(path = "{influencerId}")
    public void updateInfluencer(@PathVariable("influencerId") Long influencerId, @RequestBody Influencer influencer) {
        influencerService.updateInfluencer(influencerId, influencer);
    }

}
