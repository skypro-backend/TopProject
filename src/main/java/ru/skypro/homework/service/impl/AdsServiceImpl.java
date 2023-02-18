package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.webjars.NotFoundException;
import ru.skypro.homework.dto.CreateAdsDto;
import ru.skypro.homework.entity.Ads;
import ru.skypro.homework.entity.AdsComment;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.mapper.AdsMapper;
import ru.skypro.homework.repository.AdsCommentRepository;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.service.AdsService;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;

import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static ru.skypro.homework.security.SecurityUtils.*;

@Transactional
@RequiredArgsConstructor
@Service
public class AdsServiceImpl implements AdsService {

    private final AdsMapper mapper;
    // В сервисах не должно быть мапперов.
    // Сервисы не должны зависеть от формата данных внешних систем.
    // Сервисы должны отправлять и получать сущности

    private final ImageService imagesService;
    private final UserService userService;

    private final AdsRepository adsRepository;

    private final AdsCommentRepository adsCommentRepository;

    @SneakyThrows
    @Override
    public Ads createAds(MultipartFile image, CreateAdsDto dto) {

        Ads ads = mapper.toEntity(dto);

        ads.setImage(imagesService.uploadImage(image));

        User user = userService.getUserById(getUserIdFromContext());

        ads.setAuthor(user);

        return adsRepository.save(ads);
    }

    @Transactional(readOnly = true)
    @Override
    public Ads getAdsById(long id) {

        return adsRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Объявление с id " + id + " не найдено!"));
    }

    @Transactional(readOnly = true)
    @Override
    public Collection<Ads> getAllAds() {
        return adsRepository.findAll();
    }

    @Override
    public Ads removeAdsById(long id) {

        Ads ads = getAdsById(id);

        checkPermissionToAds(ads);

        adsCommentRepository.deleteAdsCommentsByAdsId(id);

        adsRepository.delete(ads);

        return ads;
    }

    @Override
    public Ads updateAds(Ads updatedAds) {

        Ads ads = getAdsById(updatedAds.getId());

        checkPermissionToAds(ads);

        ads.setTitle(updatedAds.getTitle());
        ads.setPrice(updatedAds.getPrice());
        ads.setDescription(updatedAds.getDescription());

        return adsRepository.save(ads);
    }

    @Transactional(readOnly = true)
    @Override
    public Collection<Ads> getAdsMe() {

        return adsRepository.findAllByAuthorId(getUserIdFromContext());
    }

    @Override
    public AdsComment addAdsComment(long adKey, AdsComment adsComment) {

        User user = userService.getUserById(getUserIdFromContext());

        adsComment.setAuthor(user);
        adsComment.setAds(adsRepository.findById(adKey).orElseThrow());
        adsComment.setCreatedAt(LocalDateTime.now());

        return adsCommentRepository.save(adsComment);
    }

    @Transactional(readOnly = true)
    @Override
    public Collection<AdsComment> getAdsComments(long adKey) {

        return adsCommentRepository.findAllByAdsId(adKey);
    }

    @Transactional(readOnly = true)
    @Override
    public AdsComment getAdsComment(long adKey, long id) {

        return adsCommentRepository.findByIdAndAdsId(id, adKey)
                .orElseThrow(() -> new NotFoundException(String.format("Комментарий с id %d, " +
                        "принадлежащий объявлению с id %d не найден!", id, adKey)));
    }

    @Override
    public AdsComment deleteAdsComment(long adKey, long id) {

        AdsComment adsComment = getAdsComment(adKey, id);

        checkPermissionToAdsComment(adsComment);

        adsCommentRepository.delete(adsComment);

        return adsComment;
    }

    @Override
    public AdsComment updateAdsComment(long adKey, long id, AdsComment updatedAdsComment) {

        AdsComment adsComment = getAdsComment(adKey, id);

        checkPermissionToAdsComment(adsComment);

        adsComment.setText(updatedAdsComment.getText());

        return adsCommentRepository.save(adsComment);
    }

    @SneakyThrows
    @Override
    public Ads updateAdsImage(Ads ads, Image image) {
        checkPermissionToAds(ads);

        ads.setImage(image);

        return adsRepository.save(ads);
    }
}









