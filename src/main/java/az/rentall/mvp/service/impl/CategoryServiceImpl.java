package az.rentall.mvp.service.impl;


import az.rentall.mvp.exception.ProductNotFoundException;
import az.rentall.mvp.mapper.CategoryMapper;
import az.rentall.mvp.mapper.ProductMapper;
import az.rentall.mvp.model.dto.request.CategoryRequest;
import az.rentall.mvp.model.dto.response.CategoryResponse;
import az.rentall.mvp.model.entity.CategoriesEntity;
import az.rentall.mvp.model.entity.ProductEntity;
import az.rentall.mvp.repository.CategoriesRepository;
import az.rentall.mvp.repository.ProductRepository;
import az.rentall.mvp.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryMapper categoryMapper;
    private final CategoriesRepository categoriesRepository;

    @Override
    public CategoryResponse createCategory(CategoryRequest categoryRequest) {
        CategoriesEntity categoriesEntity = categoryMapper.toEntity(categoryRequest);
        categoriesRepository.save(categoriesEntity);
        return categoryMapper.toResponseDto(categoriesEntity);
    }

    @Override
    public CategoryResponse findById(Long id) {
        return categoriesRepository.findById(id)
                .map(categoryMapper :: toResponseDto)
                .orElseThrow(() -> new ProductNotFoundException("Category is not found by id: " + id));
    }

    @Override
    public List<CategoryResponse> findAllCategories() {
        return categoriesRepository.findAll()
                .stream().map(categoryMapper :: toResponseDto)
                .toList();
    }

    @Override
    public void updateCategory(CategoryRequest categoryRequest, Long id) {
        CategoriesEntity categoriesEntity = categoryMapper.toEntity(categoryRequest);
        categoriesEntity.setId(id);
        categoriesRepository.save(categoriesEntity);
    }
}
