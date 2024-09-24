package az.rentall.mvp.repository;

import az.rentall.mvp.entity.CategoriesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriesRepository extends JpaRepository<CategoriesEntity, Long> {
}
