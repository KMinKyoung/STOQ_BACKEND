package me.minkyoung.stoq_back.repository;

import me.minkyoung.stoq_back.domain.ReservationStatus;
import me.minkyoung.stoq_back.entity.Reservation;
import me.minkyoung.stoq_back.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {
    //동시간대 유효한 예약이 최대 1개만 존재  -> Optinal
    //중복방지
    Optional<Reservation> findBySeatIdAndStatusAndStartTimeLessThanEqualAndEndTimeGreaterThan(Long seatId, ReservationStatus status, LocalDateTime now1, LocalDateTime now2);
   // 좌석 사용 여부
    Optional<Reservation> findBySeatAndStatusAndEndTimeAfter(Seat seat, ReservationStatus status, LocalDateTime now);
    //예약 취소
    Optional<Reservation> findByIdAndUser_Id(Long reservationId, Long userId);
    //예약 만료처리 - 스케쥴러용
    List<Reservation> findByStatusAndEndTimeBefore(ReservationStatus status, LocalDateTime time);
    //예약 만료처리 - 좌석 1개만
    List<Reservation> findFirstBySeatAndStatusAndEndTimeBefore(Seat seat, ReservationStatus status, LocalDateTime time);
    //내가 옝갸한 좌석
    List<Reservation> findByUser_IdAndStatus(Long userId, ReservationStatus status);

    // 추후 실시간 시간 처리에 이용될 예정
}
