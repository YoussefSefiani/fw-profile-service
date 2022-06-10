package fw.profileservice.repository;

import fw.profileservice.model.Country;
import fw.profileservice.model.Sector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SectorRepository extends JpaRepository<Sector, Long> {

    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM sector s where s.user_id_influencer = ?1")
    void deleteByUserIdInfluencer(Long userId);

    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM sector s where s.user_id_brand = ?1")
    void deleteByUserIdBrand(Long userId);
}
