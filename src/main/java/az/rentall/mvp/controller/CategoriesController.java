package az.rentall.mvp.controller;

import az.rentall.mvp.model.dto.request.CategoryRequest;
import az.rentall.mvp.model.dto.request.UserRequest;
import az.rentall.mvp.model.dto.response.CategoryResponse;
import az.rentall.mvp.model.dto.response.UserResponse;
import az.rentall.mvp.service.CategoryService;
import az.rentall.mvp.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoriesController {
    private final CategoryService categoryService;

    public CategoriesController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryResponse createUser(@RequestBody CategoryRequest categoryRequest) {
        return categoryService.createCategory(categoryRequest);
    }

    @GetMapping("/{id}")
    public CategoryResponse findById(@PathVariable Long id){
        return categoryService.findById(id);
    }

    @GetMapping
    public List<CategoryResponse> findAllCategories(){
        return categoryService.findAllCategories();
    }

    @PutMapping("/{id}")
    public void updateCategory (@RequestBody CategoryRequest categoryRequest, @PathVariable Long id){
        categoryService.updateCategory(categoryRequest, id);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id){
        categoryService.deleteCategory(id);
    }
}
