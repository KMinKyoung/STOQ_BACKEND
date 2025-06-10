package me.minkyoung.stoq_back.controller;

import lombok.RequiredArgsConstructor;
import me.minkyoung.stoq_back.dto.LoginRequestDto;
import me.minkyoung.stoq_back.dto.SignupRequestDto;
import me.minkyoung.stoq_back.dto.TokenDto;
import me.minkyoung.stoq_back.dto.TokenRequestDto;
import me.minkyoung.stoq_back.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignupRequestDto signupRequestDto){
        authService.signup(signupRequestDto);
        return ResponseEntity.ok("회원가입 성공");
    }
    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody LoginRequestDto requestDto){
        TokenDto tokenDto = authService.login(requestDto.getEmail(),requestDto.getPassword());
        return ResponseEntity.ok(tokenDto);
    }
    @PostMapping("/reissue")
    public ResponseEntity<TokenDto> reissue(@RequestBody TokenRequestDto requestDto){
        TokenDto tokenDto = authService.reissue(requestDto);
        return ResponseEntity.ok(tokenDto);
    }
}
