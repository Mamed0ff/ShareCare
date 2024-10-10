package az.rentall.mvp.repository;


import az.rentall.mvp.model.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    @Query(value = "select * from products where name like %:name%", nativeQuery = true)
    public List<ProductEntity> searchProductsByName(String name);
}
