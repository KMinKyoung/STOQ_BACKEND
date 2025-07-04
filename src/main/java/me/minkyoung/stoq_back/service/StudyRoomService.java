package me.minkyoung.stoq_back.service;

import lombok.AllArgsConstructor;
import me.minkyoung.stoq_back.domain.ReservationStatus;
import me.minkyoung.stoq_back.dto.*;
import me.minkyoung.stoq_back.entity.*;
import me.minkyoung.stoq_back.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StudyRoomService {
    private final StudyRoomRepository studyRoomRepository;
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final UserTimeRepository userTimeRepository;
    private final SeatRepository seatRepository;
    private final TimeChargeService timeChargeService;

    public List<StudyRoomResponseDto> getAllStudyRooms(){

        List<StudyRoomResponseDto> studyRooms = studyRoomRepository.findAll().stream()
                .map(studyRoom -> new StudyRoomResponseDto(
                        studyRoom.getId(),
                        studyRoom.getName(),
                        studyRoom.getLocation(),
                        studyRoom.isActive()
                ))
                .collect(Collectors.toList());
        //스터디 카페가 있는지 확인, 없으면 "현재 등록된 스터디카페가 없습니다."
        if(studyRooms.isEmpty()) {
            throw new IllegalArgumentException("현재 등록된 스터디 카페가 없습니다.");
        }
        //list 형식으로 저장되어 저장 출력

        return studyRooms;
    }

    //스터디카페 상세 조회
    public StudyRoomResponseDto getStudyRoomById(Long id){
        //개별 조회
        Optional<StudyRoom> studyRoom = studyRoomRepository.findById(id);
        //만약 페이지가 없으면 "존재하지 않는 페이지입니다."
        if(studyRoom.isEmpty()){
            throw new IllegalArgumentException("존재하지 않는 페이지입니다.");
        }

        StudyRoom studyRoom1 = studyRoom.get();
        //저장할 필요는 없이 그냥 조회만 리턴
        return new StudyRoomResponseDto(
                studyRoom1.getId(),
                studyRoom1.getName(),
                studyRoom1.getLocation(),
                studyRoom1.isActive(),
                studyRoom1.getOpenTime(),
                studyRoom1.getCloseTime(),
                studyRoom1.getTotal_seats()
        );
    }

    @Transactional //예약시 데이터 일관성 유지
    //스터디룸 좌석 예약
    public ReservationResponseDto reserveSeat(ReservationRequestDto requestDto){
        //좌석 객체 생성
        Seat seat = seatRepository.findById(requestDto.getSeatId())
                .orElseThrow(()-> new IllegalArgumentException("존재하지 않는 좌석입니다."));
        //유저 객체(회원 비회원 모두 가능)생성
        User user = userRepository.findById(requestDto.getUserId())
                .orElseThrow(()-> new IllegalArgumentException("존재하지 않는 회원입니다."));

        //좌석 전체를 조회함 -> 좌석을 다 사용하는 경우 "현재 만석으로 예약이 불가능합니다."
        List<Seat> seats = seatRepository.findByStudyRoomId(seat.getStudyRoom().getId());

        long userdSeatCount = 0;
        for(Seat s : seats){
            Optional<Reservation> activeReservation = reservationRepository.findBySeatIdAndStatusAndStartTimeLessThanEqualAndEndTimeGreaterThan(
                    s.getId(),
                    ReservationStatus.RESERVED,
                    LocalDateTime.now(),
                    LocalDateTime.now()
            );
            if(activeReservation.isPresent()){
                userdSeatCount++;
            }
        }

        if(userdSeatCount >= seats.size()){
            throw new IllegalArgumentException("현재 만석으로 예약이 불가능합니다.");
        }


        //좌석 선택 -> 회원이면서 시간이 충전되어있는 경우 바로 예약 진행 및 완료/ 회원이지만 충전되지 않을 경우, "시간을 충전해야합니다." / 비회원일 경우 시간 충전이 먼저 이뤄지기에 그냥 바로 예약 진행 완료
        //좌석 사용 여부 확인
        Optional<Reservation> activeReservation = reservationRepository.findBySeatIdAndStatusAndStartTimeLessThanEqualAndEndTimeGreaterThan(
                seat.getId(),
                ReservationStatus.RESERVED,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
        if(activeReservation.isPresent()){
            throw new IllegalArgumentException("해당 좌석은 현재 사용중입니다.");
        }

        //회원일 경우
        UserTime userTime = userTimeRepository.findByUser_Id(user.getId())
                .orElseThrow(()-> new IllegalArgumentException("회원의 이용 시간이 등록되어 있지 않습니다. 시간을 충전해주세요"));
        if(userTime.getRemainingMinutes() <= 0){
            throw new IllegalArgumentException("시간을 충전해야합니다.");
        }

        //Reservation 생성 및 저장
        Reservation reservation = new Reservation();
        reservation.setSeat(seat);
        reservation.setUser(user);
        reservation.setStartTime(requestDto.getStartTime());
        reservation.setEndTime(requestDto.getEndTime());
        reservation.setStatus(ReservationStatus.RESERVED);

        reservationRepository.save(reservation);

        return new ReservationResponseDto(
                reservation.getId(),
                seat.getId(),
                seat.getStudyRoom().getId(),
                reservation.getStartTime(),
                reservation.getEndTime(),
                reservation.getStatus(),
                userTime.getRemainingMinutes()
        );
    }

    //전쳬 좌석 + 예약 가능 좌석 선택 -> 예약으로 연결
    public List<SeatStatusResponseDto> getSeatStatusList(Long studyRoomId, User user){
        //로그인한 회원인지 확인, 아닐 경우 -> 로그인 하세요
        if(user == null){
            throw new IllegalArgumentException("로그인이 필요합니다.");
        }
        //로그인했지만 시간이 충전되어있지 않을 경우 -> 시간을 충전하셔야 합니다.
        if (user.getUserTime() == null || user.getUserTime().getRemainingMinutes() <= 0) {
            throw new IllegalArgumentException("시간을 충전해야 합니다.");
        }
        //스터디룸의 전체 좌석 조회
        List<Seat> seats = seatRepository.findByStudyRoomId(studyRoomId);
        LocalDateTime now = LocalDateTime.now();

        // 각 좌석의 예약 여부 확인 후 DTO변환
        return seats.stream().map(seat ->{

            Optional<Reservation> activeReservation = reservationRepository.findBySeatAndStatusAndEndTimeAfter(
                    seat,
                    ReservationStatus.RESERVED,
                    now
            );

            boolean isAvailable = activeReservation.isEmpty();
            LocalDateTime endTime = activeReservation.map(Reservation::getStartTime).orElse(null);

            //SeatStatusResponseDto로 변환
            SeatStatusResponseDto dto = new SeatStatusResponseDto();
            dto.setId(seat.getId());
            dto.setSeatNumber(seat.getSeatNumber());
            dto.setAvailable(isAvailable);
            dto.setEndTime(endTime);
            dto.setStudyRommId(studyRoomId);

            return dto;
        }).collect(Collectors.toList());
    }

    //예약 취소
    @Transactional
    public CancelReservationResponseDto cancelReservation(Long userId, CancelReservationRequestDto requestDto){
        Reservation reservation = reservationRepository.findByIdAndUser_Id(requestDto.getReservationId(), userId)
                .orElseThrow(()-> new IllegalArgumentException("유효하지 않은 예약입니다."));
        if(reservation.getStatus() == ReservationStatus.CANCELLED){
            throw new IllegalArgumentException("이미 취소된 예약됩니다.");
        }

        //사용시간 계산
        long usedMinutes = Duration.between(reservation.getStartTime(), LocalDateTime.now()).toMinutes();
        long totalMinutes = Duration.between(reservation.getStartTime() ,reservation.getEndTime()).toMinutes();
        long refundableLong = Math.max(0,totalMinutes-usedMinutes);

        int refundable = (int) refundableLong;
        //유저에게 시간 환급
        User user = userRepository.findByIdWithTime(userId)
                .orElseThrow(() -> new IllegalArgumentException("회원이 존재하지 않습니다."));
        user.getUserTime().addMinutes(refundable);
        userRepository.save(user);
        int newRemaining = user.getUserTime().getRemainingMinutes();

        //예약 상태 없데이트
        reservation.setStatus(ReservationStatus.CANCELLED);
        reservation.setCanceledAt(LocalDateTime.now());
        reservationRepository.save(reservation);

        return new CancelReservationResponseDto(reservation.getId(), refundable, newRemaining);
    }
}
