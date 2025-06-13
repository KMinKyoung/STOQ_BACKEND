package me.minkyoung.stoq_back.dto;

import lombok.Getter;
import lombok.Setter;
import me.minkyoung.stoq_back.entity.User;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReservationRequestDto {
    private Long seat_id;
    private Long user_id;
    private LocalDateTime start_time;
    private LocalDateTime end_time;
    private int price;
}
