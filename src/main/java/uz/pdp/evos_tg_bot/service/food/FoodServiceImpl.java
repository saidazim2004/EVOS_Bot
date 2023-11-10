package uz.pdp.evos_tg_bot.service.food;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.evos_tg_bot.entity.FoodEntity;
import uz.pdp.evos_tg_bot.repository.FoodRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FoodServiceImpl implements FoodService {
    private final FoodRepository foodRepository ;
    @Override
    public Optional<FoodEntity> getByName(String foodName) {

        return foodRepository.getByFoodName(foodName);

    }
}
