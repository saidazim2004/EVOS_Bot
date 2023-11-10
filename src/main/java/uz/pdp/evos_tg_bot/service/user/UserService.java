package uz.pdp.evos_tg_bot.service.user;

import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.pdp.evos_tg_bot.entity.UserEntity;
import uz.pdp.evos_tg_bot.entity.UserState;

public interface UserService {
    UserEntity getChatId(Long chatId) throws TelegramApiException;


    void updateState(Long chatId, UserState userState);

    void save(UserEntity user);
}
