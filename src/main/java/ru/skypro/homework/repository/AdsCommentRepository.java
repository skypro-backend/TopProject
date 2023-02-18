package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.skypro.homework.entity.AdsComment;

import java.util.Collection;
import java.util.Optional;

public interface AdsCommentRepository extends JpaRepository<AdsComment, Long> {

    Collection<AdsComment> findAllByAdsId(long adsId);

    Optional<AdsComment> findByIdAndAdsId(long id, long adsId);
    // По id коммента и так можно найти коммент, id объявления не нужно

    @Modifying
    @Query("delete AdsComment a where a.ads_id =: id")
    void deleteAdsCommentsByAdsId(long id);

}
