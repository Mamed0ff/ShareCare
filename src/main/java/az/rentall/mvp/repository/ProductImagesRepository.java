package az.rentall.mvp.repository;

import az.rentall.mvp.model.entity.ProductImages;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductImagesRepository extends JpaRepository<ProductImages, Long> {
    public List<ProductImages>  findByProductIdAndStatus(Long productID, Boolean status);
}
