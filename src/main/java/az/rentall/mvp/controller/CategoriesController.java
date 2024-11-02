package az.rentall.mvp.controller;

import az.rentall.mvp.model.dto.request.CategoryRequest;

import az.rentall.mvp.model.dto.response.CategoryResponse;
import az.rentall.mvp.model.dto.response.UserResponse;
import az.rentall.mvp.service.CategoryService;
import az.rentall.mvp.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.http.entity.ContentType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoriesController {
    private final CategoryService categoryService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryResponse createCategory(@RequestPart("request") @Valid CategoryRequest categoryRequest,
                                       @RequestPart("image")MultipartFile image) {
        return categoryService.createCategory(categoryRequest,image);
    }

    @GetMapping("/{id}")
    public CategoryResponse findById(@PathVariable Long id){
        return categoryService.findById(id);
    }

    @GetMapping("/all")
    public List<CategoryResponse> findAllCategories(){
        return categoryService.findAllCategories();
    }

    @PutMapping("/{id}")
    public void updateCategory (@RequestPart("request") CategoryRequest categoryRequest, @PathVariable Long id,@RequestPart("image")MultipartFile image){
        categoryService.updateCategory(categoryRequest, id,image);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id){
        categoryService.deleteCategory(id);
    }
}
