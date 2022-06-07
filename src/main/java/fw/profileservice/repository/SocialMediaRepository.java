package fw.profileservice.repository;

import fw.profileservice.model.SocialMedia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SocialMediaRepository extends JpaRepository<SocialMedia, Long> {
    void deleteByUserIdInfluencer(Long userId);
    void deleteByUserIdBrand(Long userId);
}
