package me.minkyoung.stoq_back.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "time_product")
public class TimeProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //자동 증가
    private Long id;

    private int minutes; //충전 시간(60분, 100분 등)

    private int price; //가격
}
