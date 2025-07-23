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
    private Long studyRoomId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private ReservationStatus status;
    private int remainingMinutes;

}
