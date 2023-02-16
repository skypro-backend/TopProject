package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateAdsDto;
import ru.skypro.homework.dto.FullAdsDto;
import ru.skypro.homework.entity.Ads;

@Mapper
public interface AdsMapper extends WebMapper<AdsDto, Ads> {

    String ADS_IMAGES = "/ads/images/";

    @Override
    @Mapping(target = "author.id", source = "author")
    @Mapping(target = "image", ignore = true)
    @Mapping(source = "pk", target = "id")
    Ads toEntity(AdsDto dto);

    @Override
    @Mapping(target = "author", source = "author.id")
    @Mapping(source = "id", target = "pk")
    @Mapping(target = "image", source = "image.id", qualifiedByName = "imageMapping")
    AdsDto toDto(Ads entity);


    @Mapping(target = "author", ignore = true)
    @Mapping(target = "image", ignore = true)
    @Mapping(source = "pk", target = "id")
    Ads toEntity(CreateAdsDto dto);

    @Mapping(target = "authorFirstName", source = "author.firstName")
    @Mapping(target = "authorLastName", source = "author.lastName")
    @Mapping(target = "phone", source = "author.phone")
    @Mapping(target = "email", source = "author.email")
    @Mapping(target = "image", source = "image.id", qualifiedByName = "imageMapping")
    FullAdsDto toFullAdsDto(Ads entity);

    @Named("imageMapping")
    default String imageMapping(Long value) {
        return ADS_IMAGES + value;
    }
}
