package me.minkyoung.stoq_back.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "seat")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //자동 증가
    private Long id;

    @Column(nullable = false)
    private int seatNumber; //번호가 있어야지 사용자가 그 번호 기준으로 선택 가능

    @Column(nullable = false)
    private boolean isAvailable = true;

    @JoinColumn(name = "studyRoom_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private StudyRoom studyRoom;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
