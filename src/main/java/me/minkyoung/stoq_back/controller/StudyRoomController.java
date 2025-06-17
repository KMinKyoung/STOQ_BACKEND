package me.minkyoung.stoq_back.controller;

import lombok.RequiredArgsConstructor;
import me.minkyoung.stoq_back.dto.ReservationRequestDto;
import me.minkyoung.stoq_back.dto.ReservationResponseDto;
import me.minkyoung.stoq_back.dto.SeatStatusResponseDto;
import me.minkyoung.stoq_back.dto.StudyRoomResponseDto;
import me.minkyoung.stoq_back.entity.User;
import me.minkyoung.stoq_back.service.CustomUserDetails;
import me.minkyoung.stoq_back.service.StudyRoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class StudyRoomController {
    private final StudyRoomService studyRoomService;

    //모든 스터디룸 조회
    @GetMapping("/studyrooms")
    public ResponseEntity<List<StudyRoomResponseDto>> getAllStudyRooms() {
        List<StudyRoomResponseDto> studyRooms = studyRoomService.getAllStudyRooms();
        return ResponseEntity.ok(studyRooms);
    }

    //스터디룸 개별 조회
    @GetMapping("/studyrooms/details/{id}")
    public ResponseEntity<StudyRoomResponseDto> getStudyRoomDetails(@PathVariable Long id) {
        StudyRoomResponseDto studyRoom = studyRoomService.getStudyRoomById(id);
        return ResponseEntity.ok(studyRoom);
    }

    //예약
    @PostMapping("/reservations")
    public ResponseEntity<ReservationResponseDto> reservationSeat(@RequestBody ReservationRequestDto reservationRequestDto) {
        ReservationResponseDto responseDto = studyRoomService.reserveSeat(reservationRequestDto);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/studyrooms/{studyRoomId}/seats")
    public ResponseEntity<List<SeatStatusResponseDto>> getAllReservations(@PathVariable Long studyRoomId , @AuthenticationPrincipal CustomUserDetails userDetails) {
        User user = userDetails.getUser();
        List<SeatStatusResponseDto> responseDto = studyRoomService.getSeatStatusList(studyRoomId, user);
        return ResponseEntity.ok(responseDto);
    }


}
