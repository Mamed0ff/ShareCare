package az.rentall.mvp.mapper;

import az.rentall.mvp.model.dto.request.UserRequest;
import az.rentall.mvp.model.dto.response.UserResponse;
import az.rentall.mvp.model.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    UserEntity toEntity(UserRequest userRequest);
    UserResponse toResponseDto(UserEntity userEntity);
}
