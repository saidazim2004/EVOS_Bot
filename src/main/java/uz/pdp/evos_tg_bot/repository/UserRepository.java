package uz.pdp.evos_tg_bot.repository;

import org.springframework.stereotype.Repository;
import uz.pdp.evos_tg_bot.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends  JpaRepository<UserEntity, UUID> {

    UserEntity findByChatId(Long chatId);
    UserEntity findByPhoneNumber(String phoneNumber);
}
