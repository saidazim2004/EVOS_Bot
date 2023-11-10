package uz.pdp.evos_tg_bot.service.user;

import io.github.nazarovctrl.telegrambotspring.bot.MessageSender;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.pdp.evos_tg_bot.entity.UserEntity;
import uz.pdp.evos_tg_bot.entity.UserState;
import uz.pdp.evos_tg_bot.repository.UserRepository;
import uz.pdp.evos_tg_bot.service.messages.MessagesService;

import java.util.Optional;



@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository ;
    private final MessagesService messagesService ;


    private final MessageSender messageSender ;

    @Override
    public void save(UserEntity user) {
        userRepository.save(user);
    }

    @Override
    public void updateState(Long chatId, UserState userState) {
        UserEntity byChatId = userRepository.findByChatId(chatId);
        byChatId.setState(userState);
        userRepository.save(byChatId);
    }

    @Override
    public UserEntity getChatId(Long chatId) throws TelegramApiException {
        return userRepository.findByChatId(chatId);
    }
}
