package az.rentall.mvp.service;

import az.rentall.mvp.model.dto.request.ProductRequest;
import az.rentall.mvp.model.dto.response.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductResponse createProduct(ProductRequest productRequest);
    ProductResponse findById(Long id);
    List<ProductResponse> findAllProducts();
    void updateProduct(ProductRequest productRequest, Long id);
    List<ProductResponse> searchProductsByName(String name);
}