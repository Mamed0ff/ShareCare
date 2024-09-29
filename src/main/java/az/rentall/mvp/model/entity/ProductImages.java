package az.rentall.mvp.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "prod_images")
public class ProductImages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String path;
    private Boolean status;
    private Boolean main;
    private LocalDate uploadDate;
    private LocalDate updateDate;


    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "ProductEntity_id", referencedColumnName = "id")
    private ProductEntity product;

    @PrePersist
    protected void autoFill() {
        this.status=true;
    }
}
