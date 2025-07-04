package me.minkyoung.stoq_back.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import me.minkyoung.stoq_back.dto.AdminStudyRoomRequestDto;
import me.minkyoung.stoq_back.dto.AdminStudyRoomResponseDto;
import me.minkyoung.stoq_back.dto.StudyRoomResponseDto;
import me.minkyoung.stoq_back.entity.Seat;
import me.minkyoung.stoq_back.entity.StudyRoom;
import me.minkyoung.stoq_back.repository.SeatRepository;
import me.minkyoung.stoq_back.repository.StudyRoomRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminStudyRoomService {
    private final StudyRoomRepository studyRoomRepository;
    private final SeatRepository seatRepository;
    private final KakaoMapService kakaoMapService;

    //스터디룸 생성]
    @Transactional
    public AdminStudyRoomResponseDto createStudyRoom(AdminStudyRoomRequestDto requestDto) {
        // 📍 좌표 가져오기
        Double[] coordinates = kakaoMapService.getCoordinatesFromAddress(requestDto.getLocation());
        Double latitude = coordinates[0];
        Double longitude = coordinates[1];

        // 🏢 StudyRoom 생성 + 좌표 추가
        StudyRoom studyRoom = new StudyRoom(
                requestDto.getName(),
                requestDto.getLocation(),
                requestDto.getTotal_seats()
        );
        studyRoom.setLatitude(latitude);
        studyRoom.setLongitude(longitude);

        // 💺 좌석 자동 생성
        StudyRoom saved = studyRoomRepository.save(studyRoom);
        for (int i = 1; i <= requestDto.getTotal_seats(); i++) {
            Seat seat = new Seat();
            seat.setSeatNumber(i);
            seat.setAvailable(true);
            seat.setStudyRoom(saved);
            seatRepository.save(seat);
        }

        return new AdminStudyRoomResponseDto(
                saved.getId(),
                saved.getLocation(),
                saved.getName(),
                saved.getTotal_seats()
        );
    }

    //스터디룸 수정
    public AdminStudyRoomResponseDto updateStudyRoom(Long id, AdminStudyRoomRequestDto adminStudyRoomRequestDto) {
        StudyRoom studyRoom = studyRoomRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("해당 id의 스터디 카페를 찾을 수 없습니다"+id));
        studyRoom.setName(adminStudyRoomRequestDto.getName());
        studyRoom.setLocation(adminStudyRoomRequestDto.getLocation());
        studyRoom.setTotal_seats(adminStudyRoomRequestDto.getTotal_seats());
        StudyRoom updated = studyRoomRepository.save(studyRoom);
        return new AdminStudyRoomResponseDto(
                updated.getId(),
                updated.getName(),
                updated.getLocation(),
                updated.getTotal_seats()
        );
    }
    //스터디룸 삭제
    @Transactional
    public void deleteStudyRoom(Long id) {
        if(!studyRoomRepository.existsById(id)){
            throw new EntityNotFoundException("해당 id의 스터디룸을 찾을 수 없습니다" + id);
        }
        studyRoomRepository.deleteById(id);
    }
    //스터디룸 조회
    @Transactional(readOnly = true)
    public List<AdminStudyRoomResponseDto> findAllStudyRoom() {
        return studyRoomRepository.findAll().stream()
                .map(r -> new AdminStudyRoomResponseDto(
                        r.getId(),
                        r.getName(),
                        r.getLocation(),
                        r.getTotal_seats()
                ))
                .collect(Collectors.toList());
    }
}
