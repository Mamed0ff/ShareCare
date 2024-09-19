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
@Table (name = "products")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = false)
    private String name;

    @Column(name = "description", nullable = false, unique = false)
    private String description;

    @Column(name = "location", nullable = false, unique = false)
    private String location;

    @Column(name = "availibility start", nullable = false, unique = false)
    private LocalDateTime availibility_start;

    @Column(name = "availibility end", nullable = false, unique = false)
    private LocalDateTime availibility_end;


    @Column(name = "owner id", nullable = false, unique = true)
    private Long ownerId;

    @Column(name = "listing type", nullable = false, unique = false)
    private Long listingType;



    @ManyToOne
    @Column(name = "category", nullable = false, unique = false)
    private CategoriesEntity category;

    @OneToMany(mappedBy = "product", orphanRemoval = true)
    private List<ProdImages> images;
}
