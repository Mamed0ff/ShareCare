package az.rentall.mvp.repository;

import az.atl.academy.deyishduyushdemo1.entity.entity.CategoriesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<CategoriesEntity, Long> {

}
