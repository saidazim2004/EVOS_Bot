package uz.pdp.evos_tg_bot.service.food;

import uz.pdp.evos_tg_bot.entity.FoodEntity;

import java.util.Optional;

public interface FoodService {
    Optional<FoodEntity> getByName(String foodName);
}
