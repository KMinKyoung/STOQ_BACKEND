package me.minkyoung.stoq_back.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CancelReservationRequestDto {
    private Long reservationId;
}
