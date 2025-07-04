package me.minkyoung.stoq_back.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserInfoDto {
    private String email;
    private String name;
    private String phone;
    private boolean isMember;
    private int remainingMinutes;
}
