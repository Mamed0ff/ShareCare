package az.rentall.mvp.repository;

import az.rentall.mvp.model.entity.CategoriesEntity;
import az.rentall.mvp.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

}
