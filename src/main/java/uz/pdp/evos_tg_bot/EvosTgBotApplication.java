package uz.pdp.evos_tg_bot;

import io.github.nazarovctrl.telegrambotspring.annotation.EnableTelegramLongPollingBot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@EnableTelegramLongPollingBot
@SpringBootApplication
public class EvosTgBotApplication {

    public static void main(String[] args) {
        SpringApplication.run(EvosTgBotApplication.class , args);
    }

}
