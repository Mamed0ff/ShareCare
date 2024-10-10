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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.stream.Collectors;

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
    public Page<ProductResponse> findAllProducts(java.awt.print.Pageable pageable) {
        Page<ProductEntity> productsPage = productRepository.findAll((Pageable) pageable);
        return productsPage.map(productMapper::toResponseDto);
    }

    @Override
    public void updateProduct(ProductRequest productRequest, Long id) {
        ProductEntity productEntity = productMapper.toEntity(productRequest);
        productEntity.setId(id);
        productRepository.save(productEntity);
    }

    @Override
    public List<ProductResponse> searchProductsByName(String name) {
        if(name.isBlank() || name.isEmpty()){
            throw new NullPointerException("You cant search null value");
        }
        List<ProductEntity> products = productRepository.searchProductsByName(name);
        List<ProductResponse> responses = products.stream().map(product->productMapper.toResponseDto(product)).collect(Collectors.toList());
        return responses;
    }
}
