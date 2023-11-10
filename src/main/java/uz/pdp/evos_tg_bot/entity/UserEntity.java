package uz.pdp.evos_tg_bot.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class UserEntity extends BaseEntity {

    private Long chatId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String username;
    private Double balance;
    @Enumerated(EnumType.STRING)
    private UserState state;


    @OneToMany
    private List<FoodBoxEntity> foodBoxEntities;

}
