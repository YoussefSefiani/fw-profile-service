package fw.profileservice.repository;

import fw.profileservice.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.HashMap;
import java.util.List;

public interface BrandRepository extends JpaRepository<Brand, Long> {
}
