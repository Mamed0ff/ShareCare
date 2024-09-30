package az.rentall.mvp.mapper;

import az.rentall.mvp.model.dto.request.FaqsRequest;
import az.rentall.mvp.model.dto.response.FaqsResponse;
import az.rentall.mvp.model.entity.Faqs;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FaqsMapper {
    FaqsMapper INSTANCE = Mappers.getMapper(FaqsMapper.class);

    @Mapping(target = "category.id", source = "categoryId")
    Faqs requestToEntity(FaqsRequest faqsRequest);


    @Mapping(target = "categoryId", source = "category.id")
    FaqsResponse entityToResponse(Faqs faqs);

    void mapRequestToEntity(@MappingTarget Faqs entity, FaqsRequest request);

    List<FaqsResponse> entitiesToResponses(List<Faqs> faqs);
}
