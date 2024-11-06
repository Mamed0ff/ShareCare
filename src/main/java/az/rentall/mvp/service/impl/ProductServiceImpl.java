package az.rentall.mvp.service.impl;

import az.rentall.mvp.exception.NotFoundException;
import az.rentall.mvp.exception.ProductNotFoundException;
import az.rentall.mvp.mapper.ProductMapper;
import az.rentall.mvp.model.dto.request.ProductRequest;
import az.rentall.mvp.model.dto.response.ProductResponse;
import az.rentall.mvp.model.entity.ProductEntity;
import az.rentall.mvp.model.entity.UserEntity;
import az.rentall.mvp.repository.CategoriesRepository;
import az.rentall.mvp.repository.ProductRepository;
import az.rentall.mvp.repository.UserRepository;
import az.rentall.mvp.service.ProductImageService;
import az.rentall.mvp.service.ProductService;
import az.rentall.mvp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final UserRepository userRepository;
    private final ProductMapper productMapper;
    private final UserService userService;
    private final ProductRepository productRepository;
    private final ProductImageService imageService;
    private final CategoriesRepository categoriesRepository;

    @Override
    public ProductResponse createProduct(ProductRequest productRequest, List<MultipartFile> images) {
        categoriesRepository.findById(productRequest.getCategoryId()).orElseThrow(()->new NotFoundException("Category is not found with id : "+productRequest.getCategoryId()));
        String email = userService.getCurrentEmail();
        UserEntity user = userRepository.findByEmail(email).orElseThrow(()->new NotFoundException("USER_NOT_FOUND"));
        ProductEntity productEntity = productMapper.toEntity(productRequest);
        productEntity.setOwner(user);
        productEntity.setCreated_at(LocalDateTime.now());
        productRepository.save(productEntity);
        imageService.addImages(images,productEntity.getId());
        return productMapper.toResponseDto(productEntity);
    }

    @Override
    public ProductResponse findById(Long id) {
        ProductEntity entity= productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product is not found by id: " + id));
        entity.increaseView();
        productRepository.save(entity);
        return productMapper.toResponseDto(entity);
    }

    @Override
    public List<ProductResponse> findAllProducts(Pageable pageable) {
        Page<ProductEntity> entities = productRepository.findAll(pageable);
        List<ProductResponse> responses = new ArrayList<>();
        for(ProductEntity entity : entities){
            responses.add(productMapper.toResponseDto(entity));
        }
        return responses;
    }

    @Override
    public void updateProduct(ProductRequest productRequest, Long id,List<MultipartFile> images) {
        String email = userService.getCurrentEmail();
        UserEntity user = userRepository.findByEmail(email).orElseThrow(()->new NotFoundException("USER_NOT_FOUND"));
        ProductEntity oldEntity = productRepository.findById(id).orElseThrow(()->new NotFoundException("Product is not found with id : "+id));
        ProductEntity productEntity = productMapper.toEntity(productRequest);
        productEntity.setCreated_at(oldEntity.getCreated_at());
        productEntity.setUpdated_at(LocalDateTime.now());
        productEntity.setId(id);
        productEntity.setOwner(user);
        productRepository.save(productEntity);
        if(!images.isEmpty()){
            imageService.editImage(images,id);
        }
    }

    @Override
    public List<ProductResponse> searchProductsByName(String name) {
        if(name.isBlank() || name.isEmpty()){
            throw new NullPointerException("You can not search null value");
        }
        List<ProductEntity> products = productRepository.searchProductsByName(name.toUpperCase());
        List<ProductResponse> responses = products.stream().map(product->productMapper.toResponseDto(product)).collect(Collectors.toList());
        return responses;
    }

    @Override
    public void deleteProduct(Long id) {
        ProductEntity product = productRepository.findById(id).orElseThrow(()->new ProductNotFoundException("Product is not found with id : "+id));
        productRepository.delete(product);
    }

    @Override
    public List<ProductResponse> findByCategoryId(Long id) {
        categoriesRepository.findById(id).orElseThrow(()->new NotFoundException("Category not found with id : "+id));
        List<ProductEntity> products = productRepository.findByCategoryId(id);
        List<ProductResponse> responses = products.stream().map(product->productMapper.toResponseDto(product)).collect(Collectors.toList());
        return responses;
    }

    @Override
    public List<ProductResponse> findByUserId(Long id) {
        userRepository.findById(id).orElseThrow(()-> new NotFoundException("User is not found with id : "+id));
        List<ProductEntity> products = productRepository.findByOwnerId(id);
        List<ProductResponse> responses = products.stream().map(product->productMapper.toResponseDto(product)).collect(Collectors.toList());
        return responses;
    }


}
