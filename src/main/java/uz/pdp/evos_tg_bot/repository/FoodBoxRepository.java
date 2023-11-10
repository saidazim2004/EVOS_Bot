package uz.pdp.evos_tg_bot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.evos_tg_bot.entity.FoodBoxEntity;
import uz.pdp.evos_tg_bot.entity.UserEntity;

import java.util.UUID;
@Repository
public interface FoodBoxRepository extends JpaRepository<FoodBoxEntity, UUID> {
    FoodBoxEntity getByOwnerAndIsActive(UserEntity user , boolean active);
}
