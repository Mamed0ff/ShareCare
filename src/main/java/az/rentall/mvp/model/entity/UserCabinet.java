package az.rentall.mvp.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "user_cabinet")
public class UserCabinet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String address;
    private LocalDate creationDate;
    private LocalDate updateDate;


    @OneToOne
    private UserEntity user;
}
