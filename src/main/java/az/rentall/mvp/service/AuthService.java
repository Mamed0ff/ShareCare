package az.rentall.mvp.service;


import az.rentall.mvp.model.dto.request.UserRegisterRequest;

public interface AuthService {
    public void register(UserRegisterRequest request);
}
