package fw.profileservice.feign;

import feign.Headers;
import feign.Param;
import fw.profileservice.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(name="fw-auth-service", url="${app.services.auth.url}")
public interface UserRestConsumer {

    @GetMapping("/api/user")
    List<User> getUsers(@RequestHeader("Authorization") String token);

    @GetMapping("/api/user/{userId}")
    User getUser(@PathVariable("userId") Long userId, @RequestHeader("Authorization") String token);

    @PutMapping("/api/user/{userId}")
    void updateUser(@PathVariable("userId") Long userId, @RequestHeader("Authorization") String token, User newUser);

}
