package me.minkyoung.stoq_back.repository;

import me.minkyoung.stoq_back.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    List<User> findByIsMember(boolean isMember); //회원/ 비회원 구분해서 조회

    // 프로그래밍 조인이 아닌 쿼리 로직으로 손쉽게 불러올떄마다 조인이 발생
    @Query("SELECT u FROM User u JOIN FETCH u.userTime WHERE u.id=:id")
    Optional<User> findByIdWithTime(@Param("id") Long id);
}
