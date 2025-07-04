package me.minkyoung.stoq_back.controller;

import com.siot.IamportRestClient.response.Payment;
import lombok.RequiredArgsConstructor;
import me.minkyoung.stoq_back.dto.PaymentRequestDto;
import me.minkyoung.stoq_back.dto.TimeProductResponseDto;
import me.minkyoung.stoq_back.dto.TimeChargeRequestDto;
import me.minkyoung.stoq_back.entity.TimeProduct;
import me.minkyoung.stoq_back.repository.TimeProductRepository;
import me.minkyoung.stoq_back.service.CustomUserDetails;
import me.minkyoung.stoq_back.service.IamportService;
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
    private final IamportService iamportService;
    private final TimeProductRepository timeProductRepository;

    //충전 상품 조회
    @GetMapping("/products")
    public ResponseEntity<List<TimeProductResponseDto>> getAllProducts(){
        return ResponseEntity.ok(timeChargeService.getAllProduct());
    }

    //시간 충전 요청
    @PostMapping("/charge")
    public ResponseEntity<String> charge(@RequestBody PaymentRequestDto dto) throws Exception {
        // 1. impUid로 아임포트에 결제 조회 요청
        Payment payment = iamportService.verifyPayment(dto.getImpUid());

        // 2. 결제가 정상적으로 완료되었는지 상태 확인
        if (!"paid".equals(payment.getStatus())) {
            return ResponseEntity.badRequest().body("결제가 완료되지 않았습니다.");
        }

        // 3. 결제 금액이 상품의 가격과 일치하는지 확인
        TimeProduct product = timeProductRepository.findById(dto.getProductId())
                .orElseThrow(() -> new IllegalArgumentException("상품이 존재하지 않습니다."));

        if (payment.getAmount().intValue() != product.getPrice()) {
            return ResponseEntity.badRequest().body("결제 금액이 상품 가격과 일치하지 않습니다.");
        }

        // 4. 결제 상태와 금액이 모두 일치할 경우 시간 충전 수행
        timeChargeService.chargeTimeAfterPayment(dto.getProductId());

        // 5. 성공 응답 반환
        return ResponseEntity.ok("충전이 완료되었습니다.");
    }


}
