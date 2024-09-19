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
    Long id;

    @Column(name = "name", nullable = false, unique = false)
    private String name;

    @Column(name = "description", nullable = false, unique = false)
    private String description;

    @Column(name = "location", nullable = true, unique = false)
    private String location;

    @Column(name = "availibility start", nullable = true, unique = false)
    private LocalDateTime availibility_start;

    @Column(name = "availibility end", nullable = true, unique = false)
    private LocalDateTime availibility_end;

    @Column(name = "category id", nullable = true, unique = false)
    private Long categoryId;

    @Column(name = "owner id", nullable = true, unique = false)
    private Long ownerId;

    @Column(name = "listing type", nullable = true, unique = false)
    Long listingType;


    @OneToMany(mappedBy = "product", orphanRemoval = true)
    private List<ProdImages> images;
}
