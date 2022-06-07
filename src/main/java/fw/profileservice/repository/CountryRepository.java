package fw.profileservice.repository;

import fw.profileservice.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CountryRepository extends JpaRepository<Country, Long> {
    void deleteByUserIdInfluencer(Long userId);
    void deleteByUserIdBrand(Long userId);
}
