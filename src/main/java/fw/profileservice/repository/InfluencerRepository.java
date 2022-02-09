package fw.profileservice.repository;

import fw.profileservice.model.Influencer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InfluencerRepository extends JpaRepository<Influencer, Long> {
 //  Optional<Influencer> findInfluencerByUsername(String username);
}
