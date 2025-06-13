package me.minkyoung.stoq_back.repository;

import me.minkyoung.stoq_back.entity.UserTime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserTimeRepository extends JpaRepository<UserTime,Long> {
    Optional<UserTime> findByUser_Id(Long userId);
}
