package me.minkyoung.stoq_back.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CancelReservationResponseDto {
    private Long reservationId;
    private int refundedMinutes;
    private int remainingMinutes;
}
