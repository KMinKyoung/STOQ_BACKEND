package me.minkyoung.stoq_back.controller;

import lombok.RequiredArgsConstructor;
import me.minkyoung.stoq_back.dto.AdminStudyRoomRequestDto;
import me.minkyoung.stoq_back.dto.AdminStudyRoomResponseDto;
import me.minkyoung.stoq_back.service.AdminStudyRoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/study-rooms")
@PreAuthorize("hasRole('ADMIN')")
public class AdminStudyRoomController {
        private final AdminStudyRoomService adminStudyRoomService;

        //생성
        @PostMapping
        public ResponseEntity<AdminStudyRoomResponseDto> createAdminStudyRoom(@RequestBody AdminStudyRoomRequestDto adminStudyRoomRequestDto){
              AdminStudyRoomResponseDto room =adminStudyRoomService.createStudyRoom(adminStudyRoomRequestDto);
               return ResponseEntity.ok(room);
        }

        //수정
        @PutMapping("/{id}")
        public ResponseEntity<AdminStudyRoomResponseDto> update(@PathVariable Long id, @RequestBody AdminStudyRoomRequestDto adminStudyRoomRequestDto){
                AdminStudyRoomResponseDto  dto = adminStudyRoomService.updateStudyRoom(id,adminStudyRoomRequestDto);
                return ResponseEntity.ok(dto);
        }
        //삭제
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteAdminStudyRoom(@PathVariable Long id){
                adminStudyRoomService.deleteStudyRoom(id);
                return ResponseEntity.ok().build();
        }
        //전체조회
        @GetMapping
        public ResponseEntity<List<AdminStudyRoomResponseDto>> getAllAdminStudyRoom(){
                List<AdminStudyRoomResponseDto> dto = adminStudyRoomService.findAllStudyRoom();
                return ResponseEntity.ok(dto);
        }
}
