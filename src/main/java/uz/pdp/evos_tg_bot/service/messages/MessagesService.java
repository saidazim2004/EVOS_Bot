package uz.pdp.evos_tg_bot.service.messages;

import com.google.maps.model.LatLng;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Location;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessagesService {
    public SendMessage requestContactMessage(String chatId) {
        SendMessage sendMessage = new SendMessage(chatId, "📞Пожалуйста отправите свой номер телефона");
        sendMessage.setReplyMarkup(requestContactButton());
        return sendMessage;

    }

    public ReplyKeyboardMarkup requestContactButton() {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);

        KeyboardRow row = new KeyboardRow();
        KeyboardButton button = new KeyboardButton("📞Отправить номер телефона");
        button.setRequestContact(true);
        row.add(button);

        replyKeyboardMarkup.setKeyboard(List.of(row));
        return replyKeyboardMarkup;
    }
    public SendMessage menuMessage(String chatId) {
        SendMessage sendMessage = new SendMessage(chatId, "Выберите категорию.");
        sendMessage.setReplyMarkup(menuButton());
        return sendMessage;
    }
    public ReplyKeyboardMarkup menuButton() {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);

        List<KeyboardRow> rows = new ArrayList<>();


        KeyboardRow row1 = new KeyboardRow();
        KeyboardButton row1KeyboardButton1 = new KeyboardButton("Лаваш");
        KeyboardButton row1KeyboardButton2 = new KeyboardButton("Триндвич");
        row1.add(row1KeyboardButton1);
        row1.add(row1KeyboardButton2);


        KeyboardRow row2 = new KeyboardRow();
        KeyboardButton row2KeyboardButton1 = new KeyboardButton("Шаурма");
        KeyboardButton row2KeyboardButton2 = new KeyboardButton("Бургеры");
        row2.add(row2KeyboardButton1);
        row2.add(row2KeyboardButton2);


        KeyboardRow row3 = new KeyboardRow();
        KeyboardButton row3KeyboardButton1 = new KeyboardButton("Саб");
        KeyboardButton row3KeyboardButton2 = new KeyboardButton("Картошка");
        row3.add(row3KeyboardButton1);
        row3.add(row3KeyboardButton2);


        KeyboardRow row4 = new KeyboardRow();
        KeyboardButton row4KeyboardButton1 = new KeyboardButton("Хот-Доги");
        KeyboardButton row4KeyboardButton2 = new KeyboardButton("Снеки");
        row4.add(row4KeyboardButton1);
        row4.add(row4KeyboardButton2);


        KeyboardRow row5 = new KeyboardRow();
        KeyboardButton row5KeyboardButton1 = new KeyboardButton("Гарниры,салаты,хлеб");
        KeyboardButton row5KeyboardButton2 = new KeyboardButton("Соусы ,добавки ");
        row5.add(row5KeyboardButton1);
        row5.add(row5KeyboardButton2);


        KeyboardRow row6 = new KeyboardRow();
        KeyboardButton row6KeyboardButton1 = new KeyboardButton("Наборы(сеты)");
        KeyboardButton row6KeyboardButton2 = new KeyboardButton("Десерты");
        row6.add(row6KeyboardButton1);
        row6.add(row6KeyboardButton2);


        KeyboardRow row7 = new KeyboardRow();
        KeyboardButton row7KeyboardButton1 = new KeyboardButton("Горячие напитки");
        KeyboardButton row7KeyboardButton2 = new KeyboardButton("Холодные напитки");
        row7.add(row7KeyboardButton1);
        row7.add(row7KeyboardButton2);


        KeyboardRow row8 = new KeyboardRow();
        KeyboardButton row8KeyboardButton1 = new KeyboardButton("Комбо");
        row8.add(row8KeyboardButton1);


        KeyboardRow row9 = new KeyboardRow();
        KeyboardButton row9KeyboardButton1 = new KeyboardButton("📥Корзина");
        KeyboardButton row9KeyboardButton2 = new KeyboardButton("⬅️Hазад");
        row9.add(row9KeyboardButton1);
        row9.add(row9KeyboardButton2);


        rows.add(row1);
        rows.add(row2);
        rows.add(row3);
        rows.add(row4);
        rows.add(row5);
        rows.add(row6);
        rows.add(row7);
        rows.add(row8);
        rows.add(row9);


        replyKeyboardMarkup.setKeyboard(rows);
        return replyKeyboardMarkup;
    }

    public SendMessage welcomeMessage(String chatId) {
        SendMessage sendMessage = new SendMessage(chatId, "Выберите одно из следующих");
        sendMessage.setReplyMarkup(mainMenuButton());
        return sendMessage;
    }
    public ReplyKeyboardMarkup mainMenuButton() {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);

        List<KeyboardRow> rows = new ArrayList<>();

        KeyboardRow row1 = new KeyboardRow();
        KeyboardButton keyboardButton1= new KeyboardButton("\uD83C\uDF7D Mеню");
        row1.add(keyboardButton1);

        KeyboardRow row2 = new KeyboardRow();
        keyboardButton1 = new KeyboardButton("🛍️ Mои заказы ");
        row2.add(keyboardButton1);

        KeyboardRow row3 = new KeyboardRow();
        KeyboardButton keyboardButton3 = new KeyboardButton("✍🏻 Оставить отвыз");
        KeyboardButton keyboardButton4 = new KeyboardButton("⚙️ Настройки");
        row3.add(keyboardButton3);
        row3.add(keyboardButton4);

        rows.add(row1);
        rows.add(row2);
        rows.add(row3);

        replyKeyboardMarkup.setKeyboard(rows);
        return replyKeyboardMarkup;
    }

    public SendMessage getUserLocationMessage(Long chatId) {
        SendMessage sendMessage = new SendMessage(chatId.toString(),"Отправьте \uD83D\uDCCD геолокацию или выберите адрес доставки" );

        sendMessage.setReplyMarkup(getLocation());

        // Create a ReplyKeyboard with a "Share Location" button
        return sendMessage ;
    }

    private ReplyKeyboard getLocation() {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);

        List<KeyboardRow> keyboardRows = new ArrayList<>();

        KeyboardRow row = new KeyboardRow();
        KeyboardButton button = new KeyboardButton("📍Mои адреса");
        KeyboardButton button2 = new KeyboardButton("⬅️Hазад");
        button.setRequestLocation(true); // This button will request the user's location
        button.setText("📍Mои адреса");

        row.add(button);
        row.add(button2);

        keyboardRows.add(row);



        replyKeyboardMarkup.setKeyboard(keyboardRows);
        return replyKeyboardMarkup ;

    }


    public SendMessage addressDetermination(Long chatId, Location location) {
//        String locationText = convertLocationToTextStyle(location);
        SendMessage sendMessage = new SendMessage(chatId.toString(),"Вы уверены, что отправили правильное местоположение?");

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);


        List<KeyboardRow> keyboardRows = new ArrayList<>();



        KeyboardRow row1= new KeyboardRow();
        KeyboardButton button1 = new KeyboardButton("❌Нет");
        KeyboardButton button2= new KeyboardButton("✅Да");
        row1.add(button1);
        row1.add(button2);

        KeyboardRow row2 = new KeyboardRow();
        KeyboardButton button3 = new KeyboardButton("⬅️Hазад");
        row2.add(button3);

        keyboardRows.add(row1);
        keyboardRows.add(row2);



        replyKeyboardMarkup.setKeyboard(keyboardRows);



        sendMessage.setReplyMarkup(replyKeyboardMarkup);

        return sendMessage ;

    }
    private final String googleMapsApiKey = "AIzaSyCscOehVw_XfyF2K55kyT5NEXWWktucz0U";//AIzaSyBYkiPzovki-8pLvPFg8oCI3oN5ybCWGtA//AIzaSyCscOehVw_XfyF2K55kyT5NEXWWktucz0U

    private String convertLocationToTextStyle(Location location) {
        try {
            GeoApiContext context = new GeoApiContext.Builder()
                    .apiKey(googleMapsApiKey)
                    .build();

//            GeocodingResult[] results = GeocodingApi.reverseGeocode(context, location.getLatitude(), location.getLongitude()).await();
            GeocodingResult[] results = GeocodingApi.reverseGeocode(context, new LatLng(location.getLatitude(), location.getLongitude())).await();

            if (results.length > 0) {
                // Extract the formatted address from the results
                return results[0].formattedAddress;
            } else {
                return "Location not found or could not be converted to text style.";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error occurred while converting location to text style.";
        }

    }

    public SendPhoto sendAllLavashMenu(Long chatId) {
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setReplyMarkup(lavashMenuButton());
        sendPhoto.setChatId(chatId);
        sendPhoto.setPhoto(new InputFile("https://img.freepik.com/free-photo/lavash-roll-with-onion-and-vegetables_140725-9844.jpg?w=2000"));
        sendPhoto.setCaption("Caption for the photo");

        return sendPhoto ;
    }

    private ReplyKeyboardMarkup lavashMenuButton() {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);

        List<KeyboardRow> rows = new ArrayList<>();


        KeyboardRow row1 = new KeyboardRow();
        KeyboardButton row1KeyboardButton1 = new KeyboardButton("Лаваш с курицей");
        KeyboardButton row1KeyboardButton2 = new KeyboardButton("Лаваш говядиной и сыром");
        row1.add(row1KeyboardButton1);
        row1.add(row1KeyboardButton2);


        KeyboardRow row2 = new KeyboardRow();
        KeyboardButton row2KeyboardButton1 = new KeyboardButton("Лаваш острый с говядиной");
        KeyboardButton row2KeyboardButton2 = new KeyboardButton("Лаваш острый c курицей");
        row2.add(row2KeyboardButton1);
        row2.add(row2KeyboardButton2);


        KeyboardRow row3 = new KeyboardRow();
        KeyboardButton row3KeyboardButton1 = new KeyboardButton("Лаваш c курицей и сыром");
        KeyboardButton row3KeyboardButton2 = new KeyboardButton("Фиттер");
        row3.add(row3KeyboardButton1);
        row3.add(row3KeyboardButton2);


        KeyboardRow row4 = new KeyboardRow();
        KeyboardButton row4KeyboardButton1 = new KeyboardButton("Лаваш c говядиной");
        row4.add(row4KeyboardButton1);


        KeyboardRow row5 = new KeyboardRow();
        KeyboardButton row5KeyboardButton1 = new KeyboardButton("⬅️Hазад");
        row5.add(row5KeyboardButton1);

        rows.add(row1);
        rows.add(row2);
        rows.add(row3);
        rows.add(row4);
        rows.add(row5);

        replyKeyboardMarkup.setKeyboard(rows);
        return replyKeyboardMarkup;
    }

    public SendPhoto sendLavashwithChicken(Long chatId) {
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setReplyMarkup(myBox());
        sendPhoto.setChatId(chatId);
        sendPhoto.setPhoto(new InputFile("https://media.express24.uz/r/600/600/upload/iblock/482/4829117e14db69b3e29be266eee3a1f4.jpg"));
        sendPhoto.setCaption("Yangi bodring va pomidorlar, qarsildoq chipslar bilan lavashga oʼralgan qovurilgan tovuq filesi, bizning taʼmi oʼtkir maxsus qayla bilan\n" +
                " Narxi: 21 000 so'm");
        sendPhoto.setReplyMarkup(priceLavashwithChicken());

        return sendPhoto;
    }

    private ReplyKeyboard myBox() {

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);

        List<KeyboardRow> rows = new ArrayList<>();

        KeyboardRow row1 = new KeyboardRow();
        KeyboardButton keyboardButton = new KeyboardButton("📥Корзина");
        row1.add(keyboardButton);


        KeyboardRow row2 = new KeyboardRow();
        keyboardButton = new KeyboardButton("⬅️Hазад");
        row2.add(keyboardButton);

        rows.add(row1);
        rows.add(row2);

        replyKeyboardMarkup.setKeyboard(rows);
        return replyKeyboardMarkup;

    }

    private InlineKeyboardMarkup priceLavashwithChicken() {

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        List<InlineKeyboardButton> row = new ArrayList<>();

        InlineKeyboardButton button = new InlineKeyboardButton("Мини 1 x 21 000 сум");
        button.setCallbackData("21000");
        row.add(button);

        button = new InlineKeyboardButton("Мини 2 x 42 000 сум");
        button.setCallbackData("42000");
        row.add(button);

        rows.add(row);
        row = new ArrayList<>();

        button = new InlineKeyboardButton("Мини 3 x 63 000 сум ");
        button.setCallbackData("63000");
        row.add(button);

        button = new InlineKeyboardButton("Мини 4 x 84 000 сум");
        button.setCallbackData("84000");
        row.add(button);

        rows.add(row);
        inlineKeyboardMarkup.setKeyboard(rows);
        return inlineKeyboardMarkup;
    }


}
