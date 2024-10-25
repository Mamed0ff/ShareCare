package az.rentall.mvp.controller;

import az.rentall.mvp.model.dto.request.UserRequest;
import az.rentall.mvp.model.dto.response.UserResponse;
import az.rentall.mvp.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse createUser(@RequestBody UserRequest userRequest) {
        return userService.createUser(userRequest);
    }

    @GetMapping("/{id}")
    public UserResponse findById(@PathVariable Long id){
        return userService.findById(id);
    }

    @GetMapping
    public List<UserResponse> findAllUsers(){
        return userService.findAllUsers();
    }

    @PutMapping("/{id}")
    public void updateUser (@RequestBody UserRequest userRequest, @PathVariable Long id){
        userService.updateUser(userRequest, id);
    }
}
