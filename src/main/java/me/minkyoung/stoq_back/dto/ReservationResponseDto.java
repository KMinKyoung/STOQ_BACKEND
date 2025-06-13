package me.minkyoung.stoq_back.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import me.minkyoung.stoq_back.domain.ReservationStatus;


import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ReservationResponseDto {
    private Long reservationId;
    private Long seatId;
    private LocalDateTime start_time;
    private LocalDateTime end_time;
    private ReservationStatus status;
}
