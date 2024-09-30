package az.rentall.mvp.service;

import az.rentall.mvp.model.dto.response.ProductImageResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductImageService {
    public void addImages(List<MultipartFile> images, Long productId);
    public void editImage(List<MultipartFile> images, Long productId);
    public List<ProductImageResponse> getIMages(Long productId);
}
