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
    private Double latitude;
    private Double longitude;

    public StudyRoomResponseDto(Long id, String name, String location, boolean isActive,
                                LocalDateTime openTime, LocalDateTime closeTime,
                                int total_seats) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.isActive = isActive;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.total_seats = total_seats;
    }

    public StudyRoomResponseDto(Long id, String name, String location, boolean isActive) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.isActive = isActive;
    }

    public StudyRoomResponseDto(String name, String location, Double latitude ,Double longitude){
        this.name = name;
        this.location = location;
        this.latitude = latitude;
        this.longitude = longitude;
    }

}
