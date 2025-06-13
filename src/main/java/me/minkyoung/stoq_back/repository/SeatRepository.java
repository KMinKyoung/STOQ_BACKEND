package me.minkyoung.stoq_back.repository;

import me.minkyoung.stoq_back.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {
    List<Seat> findByStudyRoomId(Long studyRoomId);
}
