package az.rentall.mvp.mapper;

import az.rentall.mvp.model.dto.request.CategoryRequest;
import az.rentall.mvp.model.dto.response.CategoryResponse;
import az.rentall.mvp.model.entity.CategoriesEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {
    CategoriesEntity toEntity(CategoryRequest categoryRequest);
    CategoryResponse toResponseDto(CategoriesEntity categoriesEntity);
}
