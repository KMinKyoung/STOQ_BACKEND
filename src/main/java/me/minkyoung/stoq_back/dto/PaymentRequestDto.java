package me.minkyoung.stoq_back.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRequestDto {
    private String impUid;
    private Long productId;
}