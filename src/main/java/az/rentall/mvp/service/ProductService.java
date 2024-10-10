package az.rentall.mvp.service;

import az.rentall.mvp.model.dto.request.ProductRequest;
import az.rentall.mvp.model.dto.response.ProductResponse;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.util.List;

public interface ProductService {
    ProductResponse createProduct(ProductRequest productRequest);
    ProductResponse findById(Long id);
    Page<ProductResponse> findAllProducts(Pageable pageable);
    void updateProduct(ProductRequest productRequest, Long id);
}