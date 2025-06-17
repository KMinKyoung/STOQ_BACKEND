package me.minkyoung.stoq_back.repository;

import me.minkyoung.stoq_back.domain.ReservationStatus;
import me.minkyoung.stoq_back.entity.Reservation;
import me.minkyoung.stoq_back.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {
    //동시간대 유효한 예약이 최대 1개만 존재  -> Optinal
    Optional<Reservation> findBySeatIdAndStatusAndStartTimeLessThanEqualAndEndTimeGreaterThan(Long seatId, ReservationStatus status, LocalDateTime now1, LocalDateTime now2);
    Optional<Reservation> findBySeatAndStatusAndEndTimeAfter(Seat seat, ReservationStatus status, LocalDateTime now);
}
