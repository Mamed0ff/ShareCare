package az.rentall.mvp.model.entity;

import az.rentall.mvp.model.Enums.RoleType;
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
@Table (name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private String name;

    private String surname;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String gmail;

    private String password;

    private String phoneNumber;

    private LocalDateTime created_at;

    private LocalDateTime updated_at;

    private String profileImage; // Saving path here, actual picture will be stored in images folder

    private LocalDateTime uploaded_at;

    private String token;


    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private RoleType roleType;

    @OneToMany(mappedBy = "owner",cascade = CascadeType.REMOVE,orphanRemoval = true)
    private List<ProductEntity> products;

    @OneToOne(cascade = CascadeType.PERSIST,mappedBy = "user",orphanRemoval = true)
    private UserCabinet cabinet;
}
