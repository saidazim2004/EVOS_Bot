package uz.pdp.evos_tg_bot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class FoodBoxEntity extends BaseEntity{

    @OneToMany()
    @Column(name = "foods")
    private List<FoodEntity> foods;

    @ManyToOne
    private UserEntity owner;


    @Column(name = "is_active")
    private Boolean isActive ;


}