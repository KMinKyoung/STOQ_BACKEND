package me.minkyoung.stoq_back.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdminStudyRoomResponseDto {
    private Long id;
    private String name;
    private String location;
    private int total_seats;
}
