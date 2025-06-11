package me.minkyoung.stoq_back.service;

import lombok.AllArgsConstructor;
import me.minkyoung.stoq_back.dto.StudyRoomResponseDto;
import me.minkyoung.stoq_back.entity.StudyRoom;
import me.minkyoung.stoq_back.repository.StudyRoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StudyRoomService {
    private final StudyRoomRepository studyRoomRepository;

    public List<StudyRoomResponseDto> getAllStudyRooms(){

        List<StudyRoomResponseDto> studyRooms = studyRoomRepository.findAll().stream()
                .map(studyRoom -> new StudyRoomResponseDto(
                        studyRoom.getId(),
                        studyRoom.getName(),
                        studyRoom.getLocation(),
                        studyRoom.isActive()
                ))
                .collect(Collectors.toList());
        //스터디 카페가 있는지 확인, 없으면 "현재 등록된 스터디카페가 없습니다."
        if(studyRooms.isEmpty()) {
            throw new IllegalArgumentException("현재 등록된 스터디 카페가 없습니다.");
        }
        //list 형식으로 저장되어 저장 출력

        return studyRooms;
    }

    //스터디카페 상세 조회
    public StudyRoomResponseDto getStudyRoomById(Long id){
        //개별 조회
        Optional<StudyRoom> studyRoom = studyRoomRepository.findById(id);
        //만약 페이지가 없으면 "존재하지 않는 페이지입니다."
        if(studyRoom.isEmpty()){
            throw new IllegalArgumentException("존재하지 않는 페이지입니다.");
        }

        StudyRoom studyRoom1 = studyRoom.get();
        //저장할 필요는 없이 그냥 조회만 리턴
        return new StudyRoomResponseDto(
                studyRoom1.getId(),
                studyRoom1.getName(),
                studyRoom1.getLocation(),
                studyRoom1.isActive(),
                studyRoom1.getOpenTime(),
                studyRoom1.getCloseTime(),
                studyRoom1.getTotal_seats()
        );
    }
}
