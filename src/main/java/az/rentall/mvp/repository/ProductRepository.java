package az.rentall.mvp.repository;

import az.atl.academy.deyishduyushdemo1.entity.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
