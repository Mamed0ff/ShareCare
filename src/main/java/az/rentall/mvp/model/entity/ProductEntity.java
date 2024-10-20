package az.rentall.mvp.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

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

    private Integer viewCount;

    @ManyToOne
    private UserEntity owner;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.REFRESH)
    private CategoriesEntity category;

    @JsonBackReference
    @OneToMany(mappedBy = "product",cascade = CascadeType.REMOVE)
    private List<ProductImages> images;


    public void increaseView(){
        this.viewCount++;
    }

    @PrePersist
    protected void autoFill(){
        this.viewCount=0;
    }
}
