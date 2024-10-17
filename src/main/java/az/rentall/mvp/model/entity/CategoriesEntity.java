package az.rentall.mvp.model.entity;

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
    private Long id;
    private String name;
    private String description;
    private String imageUrl;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private Integer prodCount;

    //relationships
//    @OneToMany (mappedBy = "category", cascade = CascadeType.PERSIST)
//    private List<Faqs> faqs;
//
//    @OneToMany (mappedBy = "category")
//    private List<ProductEntity> products;

    @PrePersist
    private void autoFill(){
        this.prodCount=0;
    }
}
