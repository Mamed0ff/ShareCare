package az.rentall.mvp.service.impl;

import az.rentall.mvp.exception.NotFoundException;
import az.rentall.mvp.exception.ProductNotFoundException;
import az.rentall.mvp.mapper.ProductMapper;
import az.rentall.mvp.model.dto.request.ProductRequest;
import az.rentall.mvp.model.dto.response.ProductResponse;
import az.rentall.mvp.model.entity.ProductEntity;
import az.rentall.mvp.repository.CategoriesRepository;
import az.rentall.mvp.repository.ProductRepository;
import az.rentall.mvp.service.ProductImageService;
import az.rentall.mvp.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductMapper productMapper;
    private final ProductRepository productRepository;
    private final ProductImageService imageService;
    private final CategoriesRepository categoriesRepository;

    @Override
    public ProductResponse createProduct(ProductRequest productRequest, List<MultipartFile> images) {
        categoriesRepository.findById(productRequest.getCategoryId()).orElseThrow(()->new NotFoundException("Category is not found with id : "+productRequest.getCategoryId()));
        ProductEntity productEntity = productMapper.toEntity(productRequest);
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
        ProductEntity productEntity = productMapper.toEntity(productRequest);
        productEntity.setId(id);
        productRepository.save(productEntity);
        imageService.editImage(images,id);
    }

    @Override
    public List<ProductResponse> searchProductsByName(String name) {
        if(name.isBlank() || name.isEmpty()){
            throw new NullPointerException("You cant search null value");
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


}
