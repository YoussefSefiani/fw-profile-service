package fw.profileservice.repository;

import fw.profileservice.model.Country;
import fw.profileservice.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LanguageRepository  extends JpaRepository<Language, Long> {
    void deleteByUserIdInfluencer(Long userId);
    void deleteByUserIdBrand(Long userId);
}
