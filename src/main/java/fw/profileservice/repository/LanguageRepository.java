package fw.profileservice.repository;

import fw.profileservice.model.Country;
import fw.profileservice.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LanguageRepository  extends JpaRepository<Language, Long> {

    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM language l where l.user_id_influencer = ?1")
    void deleteByUserIdInfluencer(Long userId);

    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM language l where l.user_id_brand = ?1")
    void deleteByUserIdBrand(Long userId);
}
