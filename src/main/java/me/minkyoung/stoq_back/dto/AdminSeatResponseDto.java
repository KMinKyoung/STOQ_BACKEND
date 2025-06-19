package me.minkyoung.stoq_back.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdminSeatResponseDto {
    private Long study_room_id;
    private  int seatNumber;
}
