package me.minkyoung.stoq_back.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.minkyoung.stoq_back.entity.TimeProduct;

//시간 충전 결과 응답용
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TimeProductResponseDto {
    private Long id;
    private int minutes;
    private int price;

    public TimeProductResponseDto(TimeProduct product) {
        this.id = product.getId();
        this.minutes = product.getMinutes();
        this.price = product.getPrice();
    }
}
