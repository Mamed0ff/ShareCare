package az.rentall.mvp.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Setter
@Getter
@Entity
@Table(name = "faqs")
@NoArgsConstructor
@AllArgsConstructor
public class Faqs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String question;
    private String answer;
    private LocalDate createdDate;
    private LocalDate updatedDate;

    @ManyToOne
    @JoinColumn(name = "CategoriesEntity_id", referencedColumnName = "id")
    private CategoriesEntity category;
}
