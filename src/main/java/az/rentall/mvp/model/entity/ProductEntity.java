package az.rentall.mvp.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table (name = "products")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String location;

    @ManyToOne
    private UserEntity owner;

    @ManyToOne
    private CategoriesEntity category;

    @OneToMany(mappedBy = "product", orphanRemoval = true)
    private List<ProdImages> images;
}
