package az.rentall.mvp.entity;

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
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProdImages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String path;
    LocalDate uploadDate;
    LocalDate ubdateDate;


    @ManyToOne
    @JoinColumn(name = "ProductEntity_id", referencedColumnName = "id")
    ProductEntity product;
}
