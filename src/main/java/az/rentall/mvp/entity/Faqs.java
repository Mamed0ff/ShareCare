package az.rentall.mvp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Entity
@Table(name = "faqs")
public class Faqs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    String question;
    String answer;
    LocalDate createdDate;
    LocalDate updatedDate;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "CategoriesEntity_id", referencedColumnName = "id")
    CategoriesEntity category;
}
