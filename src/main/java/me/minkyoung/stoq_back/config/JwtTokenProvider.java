package me.minkyoung.stoq_back.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import me.minkyoung.stoq_back.domain.Role;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
    @Value("${jwt.secret}")
    private String secretKey;

    private final long accessTokenValidTime = 1000L * 60 *30; //30분
    private final long refreshTokenValidTime = 1000L * 60 * 60 * 24 * 7; //7일

    public String createdAccessToken(String email, Role role) {
        return createToken(email,role, accessTokenValidTime);
    }
    public String createdRefreshToken(String email) {
        return createToken(email,null, refreshTokenValidTime);
    }
    private String createToken(String email, Role role, long tokenValidTime) {
        Date now = new Date();

        Claims claims = Jwts.claims().setSubject(email);
        if (role != null) {
            claims.put("role", role.name());
        }

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + tokenValidTime))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }


    public boolean validateToken(String token) {
        try{
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public String getUserEmail(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
