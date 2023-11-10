package uz.pdp.evos_tg_bot.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class FoodEntity extends BaseEntity {

    private String foodName ;
    private double price ;
    private int amount ;
    @ManyToOne
    private FoodBoxEntity foodBox;
}
