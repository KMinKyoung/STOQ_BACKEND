package me.minkyoung.stoq_back.service;

import lombok.RequiredArgsConstructor;
import me.minkyoung.stoq_back.dto.TimeProductResponseDto;
import me.minkyoung.stoq_back.entity.TimeProduct;
import me.minkyoung.stoq_back.entity.User;
import me.minkyoung.stoq_back.entity.UserTime;
import me.minkyoung.stoq_back.repository.TimeProductRepository;
import me.minkyoung.stoq_back.repository.UserRepository;
import me.minkyoung.stoq_back.repository.UserTimeRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public void chargeTimeAfterPayment(Long productId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("회원이 존재하지 않습니다."));

        TimeProduct product = timeProductRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("충전 상품이 존재하지 않습니다."));

        UserTime userTime = userTimeRepository.findByUser_Id(user.getId())
                .orElse(new UserTime(null, user, 0, null, null));

        userTime.addMinutes(product.getMinutes());
        userTimeRepository.save(userTime);
    }

}
