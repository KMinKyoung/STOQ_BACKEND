package me.minkyoung.stoq_back.controller;

import lombok.RequiredArgsConstructor;
import me.minkyoung.stoq_back.dto.*;
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
    public ResponseEntity<ReservationResponseDto> reservationSeat(@AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestBody ReservationRequestDto reservationRequestDto) {

        Long userId = userDetails.getUser().getId();
        ReservationResponseDto responseDto = studyRoomService.reserveSeat(reservationRequestDto, userId);
        return ResponseEntity.ok(responseDto);
    }


    //좌석 자리 확인
    @GetMapping("/studyrooms/{studyRoomId}/seats")
    public ResponseEntity<List<SeatStatusResponseDto>> getAllReservations(@PathVariable Long studyRoomId , @AuthenticationPrincipal CustomUserDetails userDetails) {
        User user = userDetails.getUser();
        List<SeatStatusResponseDto> responseDto = studyRoomService.getSeatStatusList(studyRoomId, user);
        return ResponseEntity.ok(responseDto);
    }

    //예약 취소
    @PostMapping("/reservations/cancel")
    public ResponseEntity<CancelReservationResponseDto> cancelReservation(@AuthenticationPrincipal CustomUserDetails userDetails,@RequestBody CancelReservationRequestDto requestDto) {
        Long user = userDetails.getUser().getId();
        CancelReservationResponseDto responseDto = studyRoomService.cancelReservation(user, requestDto);

        return ResponseEntity.ok(responseDto);
    }
}
