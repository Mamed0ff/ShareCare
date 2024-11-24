package az.rentall.mvp.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    private Boolean isOld;

    private Integer viewCount = 0;

    private LocalDateTime created_at;

    private LocalDateTime updated_at;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity owner;


    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "category_id")
    private CategoriesEntity category;

    @JsonBackReference
    @OneToMany(mappedBy = "product",cascade = CascadeType.REMOVE)
    private List<ProductImages> images;


    public void increaseView(){
        this.viewCount++;
    }

}
