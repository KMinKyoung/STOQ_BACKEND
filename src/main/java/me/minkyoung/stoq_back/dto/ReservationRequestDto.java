package me.minkyoung.stoq_back.dto;

import lombok.Getter;
import lombok.Setter;
import me.minkyoung.stoq_back.entity.User;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReservationRequestDto {
    private Long seatId;
    private Long userId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
