package az.rentall.mvp.mapper;

import az.rentall.mvp.model.dto.request.ProductImagesRequest;
import az.rentall.mvp.model.dto.response.ProductImageResponse;
import az.rentall.mvp.model.entity.ProductImages;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductImageMapper {
    ProductImageMapper INSTANCE = Mappers.getMapper(ProductImageMapper.class);

    @Mapping(target = "product.id", source = "productId")
    ProductImages requestToEntity(ProductImagesRequest request);


    ProductImageResponse entityToResponse(ProductImages image);

    List<ProductImageResponse> entitiesToResponses(List<ProductImages> entities);
}
