package az.rentall.mvp.service.impl;

import az.rentall.mvp.exception.AlreadyExistsException;
import az.rentall.mvp.exception.NotFoundException;
import az.rentall.mvp.mapper.UserMapper;
import az.rentall.mvp.model.Enums.RoleType;
import az.rentall.mvp.model.dto.request.UserUpdateRequest;
import az.rentall.mvp.model.dto.response.UserResponse;
import az.rentall.mvp.model.entity.UserEntity;
import az.rentall.mvp.repository.UserRepository;
import az.rentall.mvp.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final AmazonS3Service amazonS3Service;
    private final UserRepository userRepository;

    @Override
    public UserResponse findById(Long id) {
        UserEntity entity = userRepository.findById(id).orElseThrow(() ->
                new NotFoundException("USER_NOT_FOUND"));
        return UserMapper.INSTANCE.toResponseDto(entity);
    }

    @Override
    public List<UserResponse> findAllUsers(Pageable pageable) {
        Page<UserEntity> entities = userRepository.findAll(pageable);
        return UserMapper.INSTANCE.entitiesToResponses(entities);
    }

    @Transactional
    @Override
    public UserResponse update(UserUpdateRequest request, MultipartFile image) {
        String email = getCurrentEmail();
        UserEntity user=userRepository.findByEmail(email).orElseThrow(()->
                new NotFoundException("USER_NOT_FOUND"));
        if(!request.getEmail().equals(user.getEmail())) {
            if(userRepository.existsByEmail(request.getEmail())) {
                throw new AlreadyExistsException("EMAIL_ALREADY_EXISTS");
            }
        }
        user.setPhotoUrl(amazonS3Service.uploadFile(image));
        UserMapper.INSTANCE.mapRequestToEntity(user,request);
        userRepository.save(user);
        return UserMapper.INSTANCE.toResponseDto(user);
    }

    @Override
    public void setRole(Long userId, String role) {
        UserEntity user = userRepository.findById(userId).orElseThrow(() ->
                new NotFoundException("USER_NOT_FOUND"));
        if(role.equalsIgnoreCase("admin")){
            user.setRoleType(RoleType.ADMIN);
        }else if(role.equalsIgnoreCase("user")){
            user.setRoleType(RoleType.USER);
        }else{
            throw new NotFoundException("ROLE_NOT_FOUND");
        }
        userRepository.save(user);
    }

    public String getCurrentEmail() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof UserDetails){
            return ((UserDetails) principal).getUsername();
        }
        else{
            return principal.toString();
        }

    }

    @Override
    public UserResponse getMyProfile() {
        String email = getCurrentEmail();
        UserEntity entity = userRepository.findByEmail(email).orElseThrow(() ->
                new NotFoundException("USER_NOT_FOUND"));
        return UserMapper.INSTANCE.toResponseDto(entity);
    }
}
