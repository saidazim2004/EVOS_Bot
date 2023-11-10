package uz.pdp.evos_tg_bot.service.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.evos_tg_bot.entity.FoodEntity;
import uz.pdp.evos_tg_bot.entity.FoodBoxEntity;
import uz.pdp.evos_tg_bot.entity.UserEntity;
import uz.pdp.evos_tg_bot.repository.FoodBoxRepository;
import uz.pdp.evos_tg_bot.repository.FoodRepository;
import uz.pdp.evos_tg_bot.service.food.FoodServiceImpl;


import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class FoodBoxServiceImpl implements FoodBoxService {

    private final FoodBoxRepository foodBoxRepository;
    private final FoodServiceImpl foodService ;
    private final FoodRepository foodRepository ;



    @Override
    public void createOrder(UserEntity currentUser, int price, String foodName,int amount) {

        Optional<FoodEntity> foodEntity = foodService.getByName(foodName);

        FoodEntity food;
        if (foodEntity.isEmpty()){

            food = FoodEntity.builder().foodName(foodName).price(price).build();
            foodRepository.save(food);

        }

        else {
            food = foodEntity.get();

        }
        getByOwnerAndIsActive(currentUser, amount, food);

    }

    private void getByOwnerAndIsActive(UserEntity currentUser, int amount, FoodEntity food) {
        FoodBoxEntity byOwnerAndActive = getByOwnerAndIsActive(currentUser);

        if (byOwnerAndActive!=null){

            food.setAmount(amount);
            byOwnerAndActive.getFoods().add(food);
            foodBoxRepository.save(byOwnerAndActive);

        } else {
            Optional<FoodEntity> foodEntityOptional = foodService.getByName(food.getFoodName());

            FoodBoxEntity foodBox = FoodBoxEntity.builder().owner(currentUser).isActive(true).foods(List.of(foodEntityOptional.get())).build();
            foodBoxRepository.save(foodBox);
        }
    }

    private FoodBoxEntity getByOwnerAndIsActive(UserEntity user){
        return foodBoxRepository.getByOwnerAndIsActive(user, true);

    }


}
