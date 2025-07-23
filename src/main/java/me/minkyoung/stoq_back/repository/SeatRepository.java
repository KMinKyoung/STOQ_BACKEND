package me.minkyoung.stoq_back.repository;

import jakarta.persistence.LockModeType;
import me.minkyoung.stoq_back.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface SeatRepository extends JpaRepository<Seat, Long> {
    List<Seat> findByStudyRoomId(Long studyRoomId);
    @Modifying
    @Transactional
    @Query("DELETE FROM Seat s WHERE s.studyRoom.id = :studyRoomId")
    void deleteByStudyRoomId(@Param("studyRoomId") Long studyRoomId);

    //동시성 제어를 위한 비관적 락
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SElECT s From Seat s WHERE s.id=:id")
    Optional<Seat> findByIdForUpdate(@Param("id") Long id);
}
