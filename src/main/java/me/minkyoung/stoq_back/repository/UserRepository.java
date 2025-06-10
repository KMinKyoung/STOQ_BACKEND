package me.minkyoung.stoq_back.repository;

import me.minkyoung.stoq_back.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    List<User> findByIsMember(boolean isMember); //회원/ 비회원 구분해서 조회
}
