package fw.profileservice.repository;

import fw.profileservice.model.SocialMedia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SocialMediaRepository extends JpaRepository<SocialMedia, Long> {

    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM social_media s where s.user_id_influencer = ?1")
    void deleteByUserIdInfluencer(Long userId);

    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM social_media s where s.user_id_brand = ?1")
    void deleteByUserIdBrand(Long userId);
}
