package me.minkyoung.stoq_back.repository;

import me.minkyoung.stoq_back.dto.StudyRoomResponseDto;
import me.minkyoung.stoq_back.entity.StudyRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudyRoomRepository extends JpaRepository<StudyRoom, Long> {
    List<StudyRoom> findAll();
    Optional<StudyRoom> findById(Long id);
}
