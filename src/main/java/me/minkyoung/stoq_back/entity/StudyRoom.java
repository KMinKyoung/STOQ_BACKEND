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
@Table(name = "study_room")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudyRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//자동 증가
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private int total_seats;


    private LocalDateTime openTime; //여는 시간
    private LocalDateTime closeTime; //닫는 시간

    @Column(nullable = false)
    private boolean isActive = true; //폐점 등 비활성화

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
    //위도
    @Column(nullable = false)
    private Double latitude;
    //경도
    @Column(nullable = false)
    private Double longitude;

    public StudyRoom(String name, String location, int total_seats){
        this.name = name;
        this.location = location;
        this.total_seats = total_seats;
    }
}
