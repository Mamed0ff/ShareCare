package az.rentall.mvp.repository;

import az.rentall.mvp.model.entity.Faqs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FaqsRepository extends JpaRepository<Faqs, Long>{
    public Faqs findByCategoryIdAndIsAnswered(Long categoryId, Boolean status);
}
