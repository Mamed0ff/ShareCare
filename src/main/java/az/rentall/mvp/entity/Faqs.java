package az.rentall.mvp.entity;

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
@FieldDefaults(level = AccessLevel.PRIVATE)
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
