package me.minkyoung.stoq_back.entity;

import jakarta.persistence.*;
import me.minkyoung.stoq_back.domain.ReservationStatus;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @JoinColumn(name ="seat_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Seat seat;

    @Column(nullable = false) //
    private LocalDateTime start_time;

    @Column(nullable = false)
    private LocalDateTime end_time;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReservationStatus status;

    @Column
    private int price;
}
