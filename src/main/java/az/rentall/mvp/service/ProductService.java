package az.rentall.mvp.service;

import az.rentall.mvp.model.dto.request.ProductRequest;
import az.rentall.mvp.model.dto.response.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {
    void createProduct(ProductRequest productRequest,List<MultipartFile> images);
    ProductResponse findById(Long id);
    List<ProductResponse> findAllProducts(Pageable pageable);
    void updateProduct(ProductRequest productRequest, Long id,List<MultipartFile> images);
    List<ProductResponse> searchProductsByName(String name);
    void deleteProduct(Long id);
    List<ProductResponse> findByCategoryId(Long id);
    List<ProductResponse> findByUserId(Long id);

}