package fw.profileservice.repository;

import fw.profileservice.model.Brand;
import fw.profileservice.model.Influencer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    Optional<Brand> findBrandByUserIdBrand(Long userId);
    boolean existsByUserIdBrand(Long userId);
    void deleteByUserIdBrand(Long userId);
}
