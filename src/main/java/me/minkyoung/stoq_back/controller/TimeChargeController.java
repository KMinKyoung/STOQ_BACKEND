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
    public ResponseEntity<Void> chargeTime(@RequestBody TimeChargeRequestDto requestDto) {
        timeChargeService.chargeTime(requestDto.getProductId());
        return ResponseEntity.ok().build();
    }

}
