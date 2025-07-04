package me.minkyoung.stoq_back.repository;

import me.minkyoung.stoq_back.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {
    List<Seat> findByStudyRoomId(Long studyRoomId);
    @Modifying
    @Transactional
    @Query("DELETE FROM Seat s WHERE s.studyRoom.id = :studyRoomId")
    void deleteByStudyRoomId(@Param("studyRoomId") Long studyRoomId);
}
