package az.rentall.mvp.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "prod_images")
public class ProdImages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String path;
    private LocalDate uploadDate;
    private LocalDate updateDate;


    @ManyToOne
    @JoinColumn(name = "ProductEntity_id", referencedColumnName = "id")
    private ProductEntity product;
}
