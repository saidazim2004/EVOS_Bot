package uz.pdp.evos_tg_bot.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public abstract class BaseEntity {
        @Id
        @GeneratedValue
        protected UUID id;

        @CreationTimestamp
        @JsonSerialize(using = LocalDateSerializer.class)
        @JsonDeserialize(using = LocalDateTimeDeserializer.class)
        protected LocalDateTime cratedDate;


        @UpdateTimestamp
        @JsonSerialize(using = LocalDateSerializer.class)
        @JsonDeserialize(using = LocalDateTimeDeserializer.class)
        protected LocalDateTime lastUpdateDate;
        protected Boolean isActive = true;
}
