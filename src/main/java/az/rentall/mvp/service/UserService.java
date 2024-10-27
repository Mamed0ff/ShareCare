package az.rentall.mvp.service;

import az.rentall.mvp.model.dto.request.ProductRequest;
import az.rentall.mvp.model.dto.request.UserRegisterRequest;

import az.rentall.mvp.model.dto.request.UserUpdateRequest;
import az.rentall.mvp.model.dto.response.ProductResponse;
import az.rentall.mvp.model.dto.response.UserResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {

    UserResponse findById(Long id);

    public List<UserResponse> findAllUsers(Pageable pageable);

    public UserResponse update(UserUpdateRequest request, MultipartFile image);

    public void setRole(Long userId, String role);

    public String getCurrentEmail();

    public UserResponse getMyProfile();
}
