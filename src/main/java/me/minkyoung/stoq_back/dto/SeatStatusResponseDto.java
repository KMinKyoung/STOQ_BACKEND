package me.minkyoung.stoq_back.dto;

import lombok.Getter;
import lombok.Setter;
import me.minkyoung.stoq_back.entity.StudyRoom;

import java.time.LocalDateTime;

@Getter
@Setter
public class SeatStatusResponseDto {
    private long id;
    private int seatNumber;
    private String status;
    private Long studyRommId;
    private boolean isAvailable;
    private LocalDateTime endTime; //좌석이 사용중일때만 값이 있음
}
