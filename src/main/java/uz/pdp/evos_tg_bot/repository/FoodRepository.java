package uz.pdp.evos_tg_bot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.evos_tg_bot.entity.FoodEntity;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface FoodRepository extends JpaRepository<FoodEntity , UUID> {
    Optional<FoodEntity> getByFoodName(String name);
}
