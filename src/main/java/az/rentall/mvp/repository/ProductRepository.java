package az.rentall.mvp.repository;


import az.rentall.mvp.model.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long>, PagingAndSortingRepository<ProductEntity,Long> {
    @Query(value = "select * from products where name like %:name%", nativeQuery = true)
    public List<ProductEntity> searchProductsByName(String name);
}
