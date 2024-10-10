package az.rentall.mvp.service;

import az.rentall.mvp.model.dto.request.CategoryRequest;
import az.rentall.mvp.model.dto.request.ProductRequest;
import az.rentall.mvp.model.dto.response.CategoryResponse;
import az.rentall.mvp.model.dto.response.ProductResponse;

import java.util.List;

public interface CategoryService {
    CategoryResponse createCategory(CategoryRequest categoryRequest);
    CategoryResponse findById(Long id);
    List<CategoryResponse> findAllCategories();
    void updateCategory(CategoryRequest categoryRequest, Long id);
}