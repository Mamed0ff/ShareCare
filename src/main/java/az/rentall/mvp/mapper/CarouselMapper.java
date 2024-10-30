package az.rentall.mvp.mapper;


import az.rentall.mvp.model.dto.request.CarouselRequest;
import az.rentall.mvp.model.dto.response.CarouselResponse;
import az.rentall.mvp.model.entity.Carousel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CarouselMapper {
    CarouselMapper INSTANCE = Mappers.getMapper(CarouselMapper.class);

    Carousel requestToEntity(CarouselRequest request);

    CarouselResponse entityToResponse(Carousel entity);

    List<CarouselResponse> entitiesToResponses(List<Carousel> entities);
}
