package me.minkyoung.stoq_back.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudyRoomResponseDto {
    private Long id;
    private String name;
    private String location;
    private boolean isActive;
    private LocalDateTime openTime;
    private LocalDateTime closeTime;
    private int total_seats;

    public StudyRoomResponseDto(Long id, String name, String location, boolean isActive) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.isActive = isActive;
    }

}
