package uz.pdp.evos_tg_bot.controller;

import io.github.nazarovctrl.telegrambotspring.bot.MessageSender;
import io.github.nazarovctrl.telegrambotspring.controller.AbstractUpdateController;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.*;
import uz.pdp.evos_tg_bot.entity.UserEntity;
import uz.pdp.evos_tg_bot.entity.UserState;
import uz.pdp.evos_tg_bot.service.messages.MessagesService;
//import uz.pdp.evos_tg_bot.service.order.FoodOrderServiceImpl;
import uz.pdp.evos_tg_bot.service.order.FoodBoxService;
import uz.pdp.evos_tg_bot.service.order.FoodBoxServiceImpl;
import uz.pdp.evos_tg_bot.service.user.UserServiceImpl;

import static uz.pdp.evos_tg_bot.entity.UserState.*;

@Component
@RequiredArgsConstructor
public class UpdateController extends AbstractUpdateController {

@Autowired
private  MessageSender messageSender ;
private final UserServiceImpl userService ;
private final MessagesService messagesService ;
private final FoodBoxServiceImpl foodBoxService ;

    @SneakyThrows
    @Override
    public void handle(Update update) {



        if (update.hasMessage()){

            Message message = update.getMessage();
            String text  = message.getText();
            Long chatId = message.getChatId();

            UserEntity currentUser = userService.getChatId(chatId);
//            System.out.println("PHONE NUMBER : "+currentUser.getPhoneNumber());

            if(currentUser == null){
                if(message.hasContact()){

                    Chat chat = message.getChat();
                    Contact contact = message.getContact();

                    UserEntity user = UserEntity.builder().chatId(chatId).firstName(contact.getFirstName())
                            .lastName(contact.getLastName())
                            .phoneNumber(contact.getPhoneNumber())
                            .username(chat.getUserName())
                            .balance(0d).build();

                    userService.save(user);
                    userService.updateState(chatId,UserState.REGISTERED);
                    messageSender.execute(messagesService.welcomeMessage(chatId.toString()));

                }else{
                    messageSender.execute(messagesService.requestContactMessage(chatId.toString()));
                }
                return;

            }

            if(message.hasLocation()){
                userService.updateState(chatId , UserState.ADDRESS_DETERMINATION);
                messageSender.execute(messagesService.addressDetermination(chatId , message.getLocation()));
                return;
            }
                switch (text) {
                    case "/start" -> {
                        messageSender.execute(messagesService.requestContactMessage(chatId.toString()));
                        currentUser.setState(UserState.REGISTERED);
                        userService.updateState(chatId, UserState.REGISTERED);
                    }
                    case "\uD83C\uDF7D Mеню" -> {
                        userService.updateState(chatId, UserState.LOCATION);
                        messageSender.execute(messagesService.getUserLocationMessage(chatId));
                    }
                    case "Лаваш" -> {
                        userService.updateState(chatId , UserState.LAVASH);
                        messageSender.execute(messagesService.sendAllLavashMenu(chatId));

                    }
            }

            switch (currentUser.getState()) {
                case MENU -> {
                    if (message.getText().equals("⬅️Hазад")){
                        userService.updateState(chatId , UserState.LOCATION);
                        messageSender.execute(messagesService.getUserLocationMessage(chatId));
                    }

                }

                case LOCATION -> {
                    if(message.getText().equals("⬅️Hазад")) {
                        userService.updateState(chatId, UserState.REGISTERED);
                        messageSender.execute(messagesService.welcomeMessage(chatId.toString()));
                    }
                }
                case ADDRESS_DETERMINATION -> {
                    if (message.getText().equals("⬅️Hазад")||message.getText().equals("❌Нет")) {
                        userService.updateState(chatId,UserState.LOCATION);
                        messageSender.execute(messagesService.getUserLocationMessage(chatId));
                    }
                    else if (message.getText().equals("✅Да")) {
                        userService.updateState(chatId , UserState.MENU);
                        messageSender.execute(messagesService.menuMessage(chatId.toString()));
                    }
                }

                case LAVASH -> {
                    if (message.getText().equals("Лаваш с курицей")){
                        userService.updateState(chatId , LAVASH_1);
                        messageSender.execute(messagesService.sendLavashwithChicken(chatId));
                    }else if (text.equals("⬅️Hазад")){
                        userService.updateState(chatId , UserState.MENU);
                        messageSender.execute(messagesService.menuMessage(chatId.toString()));
                    }
                }
//                case LAVASH_WITH_CHICKEN_WITH_CHEESE -> {
//                    System.out.println("SIRLI LAVASH");
//                    if (text.equals("21000")){
//                       foodBoxService.createOrder(currentUser,21000,"Лаваш с курицей");
//                    }
//                    else if (text.equals("⬅️Hазад")){
//                        userService.updateState(chatId , UserState.MENU);
//                        messageSender.execute(messagesService.menuMessage(chatId.toString()));
//                    }
//                }
            }

        }
        else if (update.hasCallbackQuery()){
            CallbackQuery callbackQuery = update.getCallbackQuery();
            String data = callbackQuery.getData();
            Message text= callbackQuery.getMessage();
            Long chatId = text.getChatId();

            UserEntity currentUser = userService.getChatId(chatId);

            switch (currentUser.getState()){
                case LAVASH_1 -> {

                    System.out.println("SIRLI LAVASH");
                    if (data.equals("21000")){
                        userService.updateState(chatId,LAVASH);
                        foodBoxService.createOrder(currentUser,21000,"Лаваш с курицей",1);
                    }
                    else if (data.equals("42000")){
                        userService.updateState(chatId,LAVASH);
                        foodBoxService.createOrder(currentUser,42000,"Лаваш говядиной и сыром",2);
                    }
                    else if (data.equals("⬅️Hазад")){
                        userService.updateState(chatId , UserState.MENU);
                        messageSender.execute(messagesService.menuMessage(chatId.toString()));
                    }
                }
            }
        }




    }


}
