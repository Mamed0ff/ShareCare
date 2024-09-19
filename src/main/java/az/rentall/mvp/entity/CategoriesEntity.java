package az.rentall.mvp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "categories")

public class CategoriesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name", nullable = false, unique = false)
    String name;

    @Column(name = "description", nullable = false, unique = false)
    String description;

    @Column(name = "created at", nullable = false, unique = false)
    LocalDateTime created_at;

    @OneToMany (cascade = CascadeType.MERGE, mappedBy = "category")
    private List<Faqs> faqs;

    @OneToMany (mappedBy = "category")
    private List<ProductEntity> products;
}
