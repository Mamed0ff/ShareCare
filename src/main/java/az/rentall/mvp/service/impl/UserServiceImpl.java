package az.rentall.mvp.service.impl;

import az.rentall.mvp.exception.ProductNotFoundException;
import az.rentall.mvp.mapper.UserMapper;
import az.rentall.mvp.model.dto.request.UserRequest;
import az.rentall.mvp.model.dto.response.UserResponse;
import az.rentall.mvp.model.entity.UserEntity;
import az.rentall.mvp.repository.UserRepository;
import az.rentall.mvp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @Override
    public UserResponse createUser(UserRequest userRequest) {
        UserEntity userEntity = userMapper.toEntity(userRequest);
        userRepository.save(userEntity);
        return userMapper.toResponseDto(userEntity);
    }

    @Override
    public UserResponse findById(Long id) {
        return userRepository.findById(id)
                .map(userMapper :: toResponseDto)
                .orElseThrow(() -> new ProductNotFoundException("User is not found by id: " + id));
    }

    @Override
    public List<UserResponse> findAllUsers() {
        List<UserEntity> userEntities = userRepository.findAll();
//                .stream().map(userMapper :: toResponseDto)
//                .toList();
        return userEntities.stream().map(userMapper :: toResponseDto).toList();
    }

    @Override
    public void updateUser(UserRequest userRequest, Long id) {
        UserEntity userEntity = userMapper.toEntity(userRequest);
        userEntity.setId(id);
        userRepository.save(userEntity);
    }
}
