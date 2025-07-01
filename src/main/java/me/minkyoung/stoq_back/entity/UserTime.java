package me.minkyoung.stoq_back.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_time")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //자동증가
    private Long id;

    @JoinColumn(name ="user_id")
    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    @Column(nullable = false)
    private int remainingMinutes;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public void addMinutes(int minutes) {
        this.remainingMinutes += minutes;
    }

    public void subMinutes(int minutes) {
        if(this.remainingMinutes < minutes) {
            throw new IllegalArgumentException("잔여 시간이 부족합니다");
        }
        this.remainingMinutes -= minutes;
    }
}
