package ru.skypro.homework.service;

import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.CreateAdsDto;
import ru.skypro.homework.entity.Ads;
import ru.skypro.homework.entity.AdsComment;
import ru.skypro.homework.entity.Image;

import javax.validation.Valid;
import java.util.Collection;

/**
 * Сервис для работы с объявлениями
 */
public interface AdsService {

    /**
     * Добавление объявления
     *
     * @return Ads
     */
    Ads createAds(MultipartFile image, CreateAdsDto dto);
    // В сервисный слой нельзя передавать DTO, также как нельзя из него отдавать DTO.
    // Сервисный слой должен быть полностью независим от формата входящих и исходящих данных.

    /**
     * Получение объявления по ID
     *
     * @param id ID объявления
     * @return Ads
     */
    Ads getAdsById(long id);

    /**
     * Получение всех объявлений
     *
     * @return Collection<Ads>
     */
    Collection<Ads> getAllAds();

    /**
     * Удаление объявления по ID
     *
     * @param id ID объявления
     * @return Удаленное объявление
     */
    Ads removeAdsById(long id);

    /**
     * Изменение объявления по ID
     *
     * @param updatedAds Изменённое объявление
     * @return Ads Изменённое объявление
     */
    Ads updateAds(Ads updatedAds);

    /**
     * Получение всех объявлений аутентифицированного пользователя
     *
     * @return Collection<Ads>
     */
    Collection<Ads> getAdsMe();

    /**
     * Добавление комментария к объявлению
     *
     * @param adKey      ID объявления
     * @param adsComment Объект комментария
     * @return AdsComment
     */
    AdsComment addAdsComment(long adKey, AdsComment adsComment);

    /**
     * Получение всех комментариев определённого объявления
     *
     * @param adKey ID объявления
     * @return Collection<AdsComment>
     */
    Collection<AdsComment> getAdsComments(long adKey);

    /**
     * Получение комментария по ID
     *
     * @param id    ID комментария
     * @param adKey ID объявления
     * @return Найденный комментарий
     */
    AdsComment getAdsComment(long adKey, long id);
    // А зачем для получения комментария нужен id объявления? В таблице ads_comment primary key - это id коммента.

    /**
     * Удаление комментария по ID
     *
     * @param id    ID комментария
     * @param adKey ID объявления
     * @return Удалённый комментарий
     */
    AdsComment deleteAdsComment(long adKey, long id);
    // А зачем для удаления комментария нужен id объявления? В таблице ads_comment primary key - это id коммента.

    /**
     * Изменение комментария по ID
     *
     * @param id               ID комментария
     * @param adKey            ID объявления
     * @param updateAdsComment Изменённый комментарий
     * @return Изменённый комментарий
     */
    AdsComment updateAdsComment(long adKey, long id, AdsComment updateAdsComment);
    // А зачем для изменения комментария нужен id объявления? В таблице ads_comment primary key - это id коммента.


    /**
     * Обновление картинки объявления
     *
     * @param ads   объявление
     * @param image новая картинка
     * @return Объявление с обновленной картинкой
     */
    Ads updateAdsImage(Ads ads, Image image);
}



