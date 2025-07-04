package me.minkyoung.stoq_back.service;

import lombok.RequiredArgsConstructor;
import me.minkyoung.stoq_back.config.JwtTokenProvider;
import me.minkyoung.stoq_back.domain.Role;
import me.minkyoung.stoq_back.dto.RefreshToken;
import me.minkyoung.stoq_back.dto.SignupRequestDto;
import me.minkyoung.stoq_back.dto.TokenDto;
import me.minkyoung.stoq_back.dto.TokenRequestDto;
import me.minkyoung.stoq_back.entity.User;
import me.minkyoung.stoq_back.repository.RefreshTokenRepository;
import me.minkyoung.stoq_back.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final AuthenticationManager authenticationManager;

    public TokenDto login(String email, String password){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 사용자입니다."));

        //토큰 발급
        String accessToken = jwtTokenProvider.createdAccessToken(user.getEmail(), user.getRole());
        String refreshToken = jwtTokenProvider.createdRefreshToken(user.getEmail());

        //RefreshToken 저장
        RefreshToken refresh = new RefreshToken(user.getEmail(), refreshToken);
        refreshTokenRepository.save(refresh);

        return new TokenDto(accessToken, refreshToken);
    }

    public TokenDto reissue(TokenRequestDto requestDto){
        String refreshToken = requestDto.getRefreshToken();

        if(!jwtTokenProvider.validateToken(refreshToken)){
            throw new RuntimeException("Invalid refresh token");
        }

        String email = jwtTokenProvider.getUserEmail(refreshToken);

        RefreshToken savedToken = refreshTokenRepository.findById(email)
                .orElseThrow(()-> new RuntimeException("RefreshToken not found"));

        if(!savedToken.getToken().equals(refreshToken)){
            throw new RuntimeException("token mismatch");
        }
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        //새토큰 발급
        String newAccess = jwtTokenProvider.createdAccessToken(user.getEmail(), user.getRole());
        String newRefresh = jwtTokenProvider.createdRefreshToken(email);

        refreshTokenRepository.save(new RefreshToken(email,newRefresh));

        return new TokenDto(newAccess,newRefresh);
    }

    public void signup(SignupRequestDto requestDto){
        if(userRepository.findByEmail(requestDto.getEmail()).isPresent()){
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }

        User user = User.builder()
                .email(requestDto.getEmail())
                .password(passwordEncoder.encode(requestDto.getPassword()))
                .name(requestDto.getName())
                .phone(requestDto.getPhone())
                .isMember(true)
                .remaining_time(0)
                .role(Role.USER)
                .build();

        userRepository.save(user);
    }
}
