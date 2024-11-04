package az.rentall.mvp.repository;

import az.rentall.mvp.model.entity.Carousel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarouselRepository extends JpaRepository<Carousel,Long> {
}
