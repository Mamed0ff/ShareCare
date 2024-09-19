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
@Table (name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name", nullable = false, unique = false)
    private String name;

    @Column(name = "surname", nullable = false, unique = false)
    private String surname;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "gmail", nullable = false, unique = true)
    private String gmail;

    @Column(name = "password", nullable = false, unique = false)
    private String password;

    @Column(name = "phone number", nullable = false, unique = true)
    private String phoneNumber;

    @Column(name = "user role", nullable = false, unique = false)
    private String userRole;

    @Column(name = "created at", nullable = false, unique = false)
    private LocalDateTime created_at;

    @Column(name = "updated at", nullable = false, unique = false)
    private LocalDateTime updated_at;

    @Column(name = "profile image", nullable = false, unique = false)
    private byte[] profileImage; // Assuming you're using a BLOB or similar data type

    @Column(name = "uploaded_at", nullable = true, unique = false)
    private LocalDateTime uploaded_at;
}
