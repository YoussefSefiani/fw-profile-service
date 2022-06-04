package fw.profileservice.repository;

import fw.profileservice.model.Influencer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InfluencerRepository extends JpaRepository<Influencer, Long> {
//   Optional<Influencer> findInfluencerByUsername(String username);
    Optional<Influencer> findInfluencerByUserIdInfluencer(Long userId);
    boolean existsByUserIdInfluencer(Long userId);
    void deleteByUserIdInfluencer(Long userId);
}
