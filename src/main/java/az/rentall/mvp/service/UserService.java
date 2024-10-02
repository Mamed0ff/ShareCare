package az.rentall.mvp.service;

import az.rentall.mvp.model.dto.request.ProductRequest;
import az.rentall.mvp.model.dto.request.UserRequest;
import az.rentall.mvp.model.dto.response.ProductResponse;
import az.rentall.mvp.model.dto.response.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse createUser(UserRequest userRequest);
    UserResponse findById(Long id);
    List<UserResponse> findAllUsers();
    void updateUser(UserRequest userRequest, Long id);
}
