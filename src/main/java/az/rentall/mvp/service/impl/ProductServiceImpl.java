package az.rentall.mvp.service.impl;

import az.rentall.mvp.exception.ProductNotFoundException;
import az.rentall.mvp.mapper.ProductMapper;
import az.rentall.mvp.model.dto.request.ProductRequest;
import az.rentall.mvp.model.dto.response.ProductResponse;
import az.rentall.mvp.model.entity.ProductEntity;
import az.rentall.mvp.repository.ProductRepository;
import az.rentall.mvp.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductMapper productMapper;
    private final ProductRepository productRepository;

    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {
        ProductEntity productEntity = productMapper.toEntity(productRequest);
        productRepository.save(productEntity);
        return productMapper.toResponseDto(productEntity);
    }

    @Override
    public ProductResponse findById(Long id) {
        return productRepository.findById(id)
                .map(productMapper :: toResponseDto)
                .orElseThrow(() -> new ProductNotFoundException("Product is not found by id: " + id));
    }

    @Override
    public List<ProductResponse> findAllProducts() {
        return productRepository.findAll()
                .stream().map(productMapper :: toResponseDto)
                .toList();
    }

    @Override
    public void updateProduct(ProductRequest productRequest, Long id) {
        ProductEntity productEntity = productMapper.toEntity(productRequest);
        productEntity.setId(id);
        productRepository.save(productEntity);
    }
}
