package az.rentall.mvp.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    private Boolean isAnswered;
    private LocalDate createdDate;
    private LocalDate updatedDate;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "CategoriesEntity_id", referencedColumnName = "id")
    private CategoriesEntity category;

    @PrePersist
    protected void autoFill(){
        this.isAnswered=false;
    }
}
