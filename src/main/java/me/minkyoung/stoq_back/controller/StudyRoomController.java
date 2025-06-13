package me.minkyoung.stoq_back.controller;

import lombok.RequiredArgsConstructor;
import me.minkyoung.stoq_back.dto.ReservationRequestDto;
import me.minkyoung.stoq_back.dto.ReservationResponseDto;
import me.minkyoung.stoq_back.dto.StudyRoomResponseDto;
import me.minkyoung.stoq_back.service.StudyRoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class StudyRoomController {
    private final StudyRoomService studyRoomService;

    @GetMapping("/studyrooms")
    public ResponseEntity<List<StudyRoomResponseDto>> getAllStudyRooms() {
        List<StudyRoomResponseDto> studyRooms = studyRoomService.getAllStudyRooms();
        return ResponseEntity.ok(studyRooms);
    }

    @GetMapping("/studyrooms/details/{id}")
    public ResponseEntity<StudyRoomResponseDto> getStudyRoomDetails(@PathVariable Long id) {
        StudyRoomResponseDto studyRoom = studyRoomService.getStudyRoomById(id);
        return ResponseEntity.ok(studyRoom);
    }

    @PostMapping("/reservations")
    public ResponseEntity<ReservationResponseDto> reservationSeat(@RequestBody ReservationRequestDto reservationRequestDto) {
        ReservationResponseDto responseDto = studyRoomService.reserveSeat(reservationRequestDto);
        return ResponseEntity.ok(responseDto);
    }



}
