package az.rentall.mvp.controller;

import az.rentall.mvp.model.dto.request.UserRegisterRequest;
import az.rentall.mvp.model.dto.request.UserUpdateRequest;
import az.rentall.mvp.model.dto.response.UserResponse;
import az.rentall.mvp.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @GetMapping("/my")
    public UserResponse getMyProfile(){
     return userService.getMyProfile();
    }

    @GetMapping("/{id}")
    public UserResponse findById(@PathVariable Long id){
        return userService.findById(id);
    }

    @GetMapping("/all")
    public List<UserResponse> findAllUsers(Pageable pageable){
        return userService.findAllUsers(pageable);
    }

    @PutMapping("/update")
    public void updateUser (@RequestPart("request") @Valid UserUpdateRequest request,
                            @RequestPart("image") MultipartFile image){
        userService.update(request,image);
    }

    @PatchMapping("/set-role")
    public void setRole(@RequestParam(name = "user") Long userId,
                        @RequestParam(name = "role") String role) {
        userService.setRole(userId, role);
    }
}
