package az.rentall.mvp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

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
    String name;

    @Column(name = "description", nullable = false, unique = false)
    String description;

    @Column(name = "location", nullable = true, unique = false)
    String location;

    @Column(name = "availibility start", nullable = true, unique = false)
    LocalDateTime availibility_start;

    @Column(name = "availibility end", nullable = true, unique = false)
    LocalDateTime availibility_end;

    @Column(name = "category id", nullable = true, unique = false)
    Long categoryId;

    @Column(name = "owner id", nullable = true, unique = false)
    Long ownerId;

    @Column(name = "listing type", nullable = true, unique = false)
    Long listingType;
}
