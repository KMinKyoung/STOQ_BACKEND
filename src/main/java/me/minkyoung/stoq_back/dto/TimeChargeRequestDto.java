package me.minkyoung.stoq_back.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

//시간 충전 요청용
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TimeChargeRequestDto {
    private Long productId;
}
