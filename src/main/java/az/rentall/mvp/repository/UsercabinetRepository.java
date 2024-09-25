package az.rentall.mvp.repository;

import az.rentall.mvp.model.entity.UserCabinet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsercabinetRepository extends JpaRepository<UserCabinet,Long> {
}
