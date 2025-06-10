package me.minkyoung.stoq_back.repository;

import me.minkyoung.stoq_back.dto.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, String> {
}
