package me.minkyoung.stoq_back.controller;

import lombok.RequiredArgsConstructor;
import me.minkyoung.stoq_back.domain.ReservationStatus;
import me.minkyoung.stoq_back.dto.ReservationResponseDto;
import me.minkyoung.stoq_back.dto.UserInfoDto;
import me.minkyoung.stoq_back.entity.Reservation;
import me.minkyoung.stoq_back.entity.User;
import me.minkyoung.stoq_back.entity.UserTime;
import me.minkyoung.stoq_back.repository.ReservationRepository;
import me.minkyoung.stoq_back.repository.UserRepository;
import me.minkyoung.stoq_back.repository.UserTimeRepository;
import me.minkyoung.stoq_back.service.CustomUserDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/users")
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;
    private final UserTimeRepository userTimeRepository;
    private final ReservationRepository reservationRepository;

    @GetMapping("/me")
    public ResponseEntity<UserInfoDto> getMyInfo(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
        UserTime userTime = userTimeRepository.findByUser_Id(user.getId())
                .orElseThrow(() -> new IllegalArgumentException("잔여 시간이 존재하지 않습니다."));

        UserInfoDto userInfoDto = new UserInfoDto(
                user.getEmail(),
                user.getName(),
                user.getPhone(),
                user.isMember(),
                userTime.getRemainingMinutes()
        );
        return ResponseEntity.ok(userInfoDto);
    }

    @GetMapping("/reservations/my")
    public ResponseEntity<List<ReservationResponseDto>> getMyReservations(@AuthenticationPrincipal CustomUserDetails userDetails) {
        Long userId = userDetails.getUser().getId();
        List<Reservation> reservations = reservationRepository.findByUser_IdAndStatus(userId, ReservationStatus.RESERVED);

        List<ReservationResponseDto> result = reservations.stream()
                .map(reservation -> new ReservationResponseDto(
                        reservation.getId(),
                        reservation.getSeat().getId(),
                        reservation.getStudyRoom().getId(),
                        reservation.getStartTime(),
                        reservation.getEndTime(),
                        reservation.getStatus(),
                        reservation.getUserTime() != null ? reservation.getUserTime().getRemainingMinutes() : 0
                ))
                .toList();

        return ResponseEntity.ok(result);
    }
}
