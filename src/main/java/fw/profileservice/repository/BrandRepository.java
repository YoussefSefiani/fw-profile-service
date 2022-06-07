package fw.profileservice.repository;

import fw.profileservice.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    Optional<Brand> findByUserIdBrand(Long userId);
    boolean existsByUserIdBrand(Long userId);
    void deleteByUserIdBrand(Long userId);
}
