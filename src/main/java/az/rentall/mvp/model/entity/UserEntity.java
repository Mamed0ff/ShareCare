package az.rentall.mvp.model.entity;

import az.rentall.mvp.model.Enums.RoleType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table (name = "users")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    String surname;

    @Column(nullable = false, unique = true)
    String username;

    @Column(nullable = false, unique = true)
    String email;

    String password;

    String phoneNumber;

    LocalDateTime created_at;

    LocalDateTime updated_at;

    String profileImage; // Saving path here, actual picture will be stored in images folder

    LocalDateTime uploaded_at;

    String token;

    Boolean isVerified;


    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    RoleType roleType;

//    @OneToMany(mappedBy = "owner",cascade = CascadeType.REMOVE,orphanRemoval = true)
//    List<ProductEntity> products;

}
