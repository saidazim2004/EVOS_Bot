package uz.pdp.evos_tg_bot.service.order;

import uz.pdp.evos_tg_bot.entity.FoodEntity;
import uz.pdp.evos_tg_bot.entity.UserEntity;

public interface FoodBoxService {
    void createOrder(UserEntity currentUser, int price, String foodName,int count);


}
