package az.rentall.mvp.mapper;

import az.rentall.mvp.model.dto.request.UserRegisterRequest;
import az.rentall.mvp.model.dto.request.UserUpdateRequest;
import az.rentall.mvp.model.dto.response.UserResponse;
import az.rentall.mvp.model.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserEntity registerRequestToEntity(UserRegisterRequest request);

    UserResponse toResponseDto(UserEntity userEntity);

    List<UserResponse> entitiesToResponses(Page<UserEntity> entities);

    void mapRequestToEntity(@MappingTarget UserEntity entity, UserUpdateRequest request);
}
