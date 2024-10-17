package az.rentall.mvp.service.impl;


import az.rentall.mvp.exception.NotFoundException;
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
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryMapper categoryMapper;
    private final CategoriesRepository categoriesRepository;
    private final AmazonS3Service amazonS3Service;

    @Override
    public CategoryResponse createCategory(CategoryRequest categoryRequest, MultipartFile image) {
        CategoriesEntity categoriesEntity = categoryMapper.toEntity(categoryRequest);
        categoriesEntity.setCreated_at(LocalDateTime.now());
        categoriesEntity.setImageUrl(amazonS3Service.uploadFile(image));
        categoriesRepository.save(categoriesEntity);
        return categoryMapper.toResponseDto(categoriesEntity);
    }

    @Override
    public CategoryResponse findById(Long id) {
        return categoriesRepository.findById(id)
                .map(categoryMapper :: toResponseDto)
                .orElseThrow(() -> new NotFoundException("Category is not found by id: " + id));
    }

    @Override
    public List<CategoryResponse> findAllCategories() {
        return categoriesRepository.findAll()
                .stream().map(categoryMapper :: toResponseDto)
                .toList();
    }

    @Override
    public void updateCategory(CategoryRequest categoryRequest, Long id,MultipartFile image) {
        CategoriesEntity entity = categoriesRepository.findById(id).orElseThrow(()->new NotFoundException("Category is not found with id : "+id));
        CategoriesEntity updatedEntity = categoryMapper.toEntity(categoryRequest);
        updatedEntity.setId(entity.getId());
        updatedEntity.setImageUrl(amazonS3Service.uploadFile(image));
        categoriesRepository.save(updatedEntity);
    }

    @Override
    public void deleteCategory(Long id) {
        CategoriesEntity entity = categoriesRepository.findById(id).orElseThrow(()->new NotFoundException("Category is not found with id : "+id));
        categoriesRepository.delete(entity);
    }


}
