package me.minkyoung.stoq_back.controller;

import lombok.RequiredArgsConstructor;
import me.minkyoung.stoq_back.dto.TimeProductResponseDto;
import me.minkyoung.stoq_back.dto.TimeChargeRequestDto;
import me.minkyoung.stoq_back.service.CustomUserDetails;
import me.minkyoung.stoq_back.service.TimeChargeService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/times")
public class TimeChargeController {
    private final TimeChargeService timeChargeService;

    //충전 상품 조회
    @GetMapping("/products")
    public ResponseEntity<List<TimeProductResponseDto>> getAllProducts(){
        return ResponseEntity.ok(timeChargeService.getAllProduct());
    }

    //시간 충전 요청
    @PostMapping("/charge")
    public ResponseEntity<String> chargeTime(@AuthenticationPrincipal CustomUserDetails userDetails,
                                             @RequestBody TimeChargeRequestDto requestDto) {
        Long userId = userDetails.getUser().getId(); // 로그인된 사용자 ID
        timeChargeService.chargeTime(userId, requestDto.getProductId());
        return ResponseEntity.ok("충전이 완료되었습니다.");
    }
}
