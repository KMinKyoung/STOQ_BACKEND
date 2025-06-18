package me.minkyoung.stoq_back.service;

import lombok.RequiredArgsConstructor;
import me.minkyoung.stoq_back.dto.TimeProductResponseDto;
import me.minkyoung.stoq_back.entity.TimeProduct;
import me.minkyoung.stoq_back.entity.User;
import me.minkyoung.stoq_back.entity.UserTime;
import me.minkyoung.stoq_back.repository.TimeProductRepository;
import me.minkyoung.stoq_back.repository.UserRepository;
import me.minkyoung.stoq_back.repository.UserTimeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TimeChargeService {
    private final TimeProductRepository timeProductRepository;
    private final UserTimeRepository userTimeRepository;
    private final UserRepository userRepository;

    //충전 상품 전체 조회
    public List<TimeProductResponseDto> getAllProduct() {
        return timeProductRepository.findAll().stream()
                .map(TimeProductResponseDto::new)
                .toList();
    }

    //시간 충전
    @Transactional
    public void chargeTime(Long userId, Long productId) {
        //상품 조회
        TimeProduct product = timeProductRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("충전 상품이 존재하지 않습니다."));
        //회원 조회
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("회원이 존재하지 않습니다."));
        //유저 시간 조회 또는 새로 생성
        UserTime userTime = userTimeRepository.findByUser_Id(userId)
                .orElse(new UserTime(null , user, 0, null,null));
        //시간 충전
        userTime.addMinutes(product.getMinutes());
        //저장
        userTimeRepository.save(userTime);
    }
}
