package me.minkyoung.stoq_back.repository;

import me.minkyoung.stoq_back.entity.TimeProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeProductRepository extends JpaRepository<TimeProduct,Long> {
}
