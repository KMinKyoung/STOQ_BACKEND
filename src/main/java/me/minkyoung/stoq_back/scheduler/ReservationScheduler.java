//package me.minkyoung.stoq_back.scheduler;
//
//import lombok.RequiredArgsConstructor;
//import me.minkyoung.stoq_back.domain.ReservationStatus;
//import me.minkyoung.stoq_back.entity.Reservation;
//import me.minkyoung.stoq_back.repository.ReservationRepository;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//@Component
//@RequiredArgsConstructor
//public class ReservationScheduler {
//    private final ReservationRepository reservationRepository;
//
//    @Scheduled(fixedRate = 300000) //5분마다 실행
//    @Transactional
//    public void expireReservations(){
//        List<Reservation> expiredList = reservationRepository
//                .findBySeatAndStatusAndEndTimeBefore(ReservationStatus.RESERVED, LocalDateTime.now());
//
//        for(Reservation r : expiredList){
//            r.setStatus(ReservationStatus.EXPIRED);
//        }
//
//        reservationRepository.saveAll(expiredList);
//    }
//}
