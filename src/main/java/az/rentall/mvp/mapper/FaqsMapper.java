package az.rentall.mvp.mapper;

import az.rentall.mvp.model.dto.request.FaqsRequest;
import az.rentall.mvp.model.dto.response.FaqsResponse;
import az.rentall.mvp.model.entity.Faqs;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FaqsMapper {
    @Mapping(target = "category.id", source = "categoryId")
    Faqs requestToEntity(FaqsRequest faqsRequest);

    FaqsResponse entityToResponse(Faqs faqs);
}
