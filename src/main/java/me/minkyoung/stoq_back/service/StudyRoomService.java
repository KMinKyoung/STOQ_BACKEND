package me.minkyoung.stoq_back.service;

import lombok.AllArgsConstructor;
import me.minkyoung.stoq_back.dto.StudyRoomResponseDto;
import me.minkyoung.stoq_back.repository.StudyRoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;
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
}
