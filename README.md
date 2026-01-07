# STOQ(Study To Quick) READ ME
 <img src="https://github.com/user-attachments/assets/82243357-f905-49fb-962c-cf38285cab34" />
 
[시연 영상](https://youtu.be/RCE37fDnb00?si=eCjD78XVrPuz_Gi3)

## 프로젝트 소개
**전국의 제휴된 스터디카페 좌석을 온라인으로 실시간 예약할 수 있는 웹 사이트**
- 스터디룸을 자주 이용하면서 늦은 시간에 방문했을 때 빈 좌석이 없어 헛걸음을 하는 경험이 반복되었습니다. 
<br/>
이에 따라 실시간 정보를 확인할 수 없어 생기는 비효율을 줄이기 위해 사용자가 온라인으로 스터디룸 좌석을 예약하고 시간 기반으로 이용/ 관리할 수 있는 시스템이 필요하다고 판단했습니다.

<br/> <br/>

**주요 목적**
1. 스터디카페 좌석을 사전 확보하여 효율적인 운영 가능
2. 사용자 입장에석도 즉시 좌석 확보 및 시간 관리 가능

<br/> 

## 1. 개발 환경
- 언어 : JAVA(JDK-17)
- 프레임워크 : Spring Boot(백엔드), next.js(프론트엔드)
- 빌드 및 의존성 관리 : Gradle
- 데이터베이스 : MySQL
- 테스트 : Postman
- 서버 : AWS EC2(종료)
- [요구사항정의서 & 기능 명세서](https://docs.google.com/spreadsheets/d/13WBT6Bmk5UBr_YgEfOiT6jOvot2WiY244FvpIn3I2Hs/edit?usp=sharing)

<br/>

## 2. 채택한 개발 기술
### React
- 컴포넌트 기반 구조를 적용해 UI를 재사용 가능하게 만들고, 유지보수성을 높였습니다.
- 헤더, 푸터, 버튼과 같이 반복되는 요소를 컴포넌트화하여 개발 효율성을 높일 수 있습니다.

### Next.js
- React 기반 프레임워크이기에 페이지 기반 라우팅을 쉽게 구현할 수 있습니다.
- 개발 서버를 통해 빠르게 기능 테스트하고, 프론트엔드 개발 속도를 높일 수 있었습니다.

### Java 17 & Spirng Boot 3.x
- 안정적인 LTS 버전인 Java 17을 사용하여 최신 문법과 안정성을 동시에 확보했습니다.
- Spring Boot 설정으로 빠르게 REST API 서버를 구축할 수 있었습니다.

<br/>

## 3. 프로젝트 구조
```
📦src
┣ 📂main
┃ ┣ 📂java
┃ ┃ ┗ 📂me
┃ ┃ ┃ ┗ 📂minkyoung
┃ ┃ ┃ ┃ ┗ 📂stoq_back
┃ ┃ ┃ ┃ ┃ ┣ 📂config
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AdminInitializer.java
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CorsGlobalConfig.java
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜JwtAuthenticationFilter.java
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜JwtTokenProvider.java
┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜SecurityConfig.java
┃ ┃ ┃ ┃ ┃ ┣ 📂controller
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AdminStudyRoomController.java
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AuthController.java
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜StudyRoomController.java
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜TimeChargeController.java
┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserController.java
┃ ┃ ┃ ┃ ┃ ┣ 📂domain
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ReservationStatus.java
┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜Role.java
┃ ┃ ┃ ┃ ┃ ┣ 📂dto
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AdminSeatRequestDto.java
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AdminSeatResponseDto.java
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AdminStudyRoomRequestDto.java
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AdminStudyRoomResponseDto.java
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CancelReservationRequestDto.java
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CancelReservationResponseDto.java
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜KakaoApiResponse.java
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜LoginRequestDto.java
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜MapDto.java
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜PaymentRequestDto.java
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜RefreshToken.java
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ReservationRequestDto.java
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ReservationResponseDto.java
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜SeatStatusResponseDto.java
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜SignupRequestDto.java
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜StudyRoomResponseDto.java
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜TimeChargeRequestDto.java
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜TimeProductResponseDto.java
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜TokenDto.java
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜TokenRequestDto.java
┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserInfoDto.java
┃ ┃ ┃ ┃ ┃ ┣ 📂entity
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Reservation.java
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Seat.java
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜StudyRoom.java
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜TimeProduct.java
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜User.java
┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserTime.java
┃ ┃ ┃ ┃ ┃ ┣ 📂repository
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜RefreshTokenRepository.java
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ReservationRepository.java
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜SeatRepository.java
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜StudyRoomRepository.java
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜TimeProductRepository.java
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜UserRepository.java
┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserTimeRepository.java
┃ ┃ ┃ ┃ ┃ ┣ 📂scheduler
┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ReservationScheduler.java
┃ ┃ ┃ ┃ ┃ ┣ 📂service
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AdminStudyRoomService.java
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AuthService.java
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CustomUserDetails.java
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜IamportConfig.java
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜IamportService.java
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜KakaoMapService.java
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜StudyRoomService.java
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜TimeChargeService.java
┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserDetailService.java
┃ ┃ ┃ ┃ ┃ ┗ 📜StoqBackApplication.java
┃ ┗ 📂resources
┃ ┃ ┣ 📜application-local.yml
┃ ┃ ┣ 📜application-prod.yml
┃ ┃ ┣ 📜application.properties
┃ ┃ ┗ 📜application.yml
┗ 📂test
┃ ┗ 📂java
┃ ┃ ┗ 📂me
┃ ┃ ┃ ┗ 📂minkyoung
┃ ┃ ┃ ┃ ┗ 📂stoq_back
┃ ┃ ┃ ┃ ┃ ┣ 📂sevice
┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ReservationServiceTest.java
┃ ┃ ┃ ┃ ┃ ┗ 📜StoqBackApplicationTests.java
```

## 4. 개발기간
- 기능 구현 기간 : 2025.06 ~ 2025.08

## 5. 페이지별 기능
### 로그인 페이지
<img width="846" height="706" alt="Image" src="https://github.com/user-attachments/assets/8a374648-dff1-4e2c-9ea6-03392ddbdc7e" />

- 이메일, 비밀번호를 이용한 JWT 기반 로그인

### 회원가입 페이지
<img width="831" height="722" alt="Image" src="https://github.com/user-attachments/assets/474bc45d-3e75-4948-b7ee-4750204eb0b3" />

- 이메일, 이름, 전화번호, 비밀번호를 함께 등록하여 JWT 기반 회원가입

### 스터디룸 전체 조회
<img width="1172" height="538" alt="Image" src="https://github.com/user-attachments/assets/f992c974-c076-426f-8872-ab3d89187fc9" />

- 현재 운영중인 스터디룸에 대한 전체 조회
- 로그인한 회원의 경우 마이페이지가 제공됨

### 스터디룸 상세 조회
<img width="848" height="715" alt="Image" src="https://github.com/user-attachments/assets/2766a0fc-052a-4d6f-83ca-7f59ab3375b7" />

- 스터디룸에 대한 세부적인 내용 조회

### 마이페이지
<img width="839" height="691" alt="Image" src="https://github.com/user-attachments/assets/a532c924-27b5-413f-9430-cf3f78fa66e7" />

- 사용자에 대한 정보 및 예약 목록 확인 가능
- 예약 취소 시 기존 시간에서 사용 시간을 차감하여 반환

### 시간 충전
<img width="1097" height="578" alt="Image" src="https://github.com/user-attachments/assets/6c19a94b-e24a-4964-a6ce-494950566f46" />

- 시간을 충전하여 스터디룸 좌석 예약 가능
- 아임포트의 결제 API와 연동되어 (테스트) 간편 결제가 진행

### 좌석 선택
<img width="830" height="693" alt="Image" src="https://github.com/user-attachments/assets/20388dbe-8844-4b19-b767-fecca5b3b208" />

- 예약 시간이 있을 경우, 좌석 예약이 진행되며 예약 시간이 없을 경우, 시간 충전이 필요하다는 메시지가 뜨며 예약 불가능

### 관리자 페이지
<img width="844" height="724" alt="Image" src="https://github.com/user-attachments/assets/f894be44-f6ab-4b2d-9e92-94f2af36e32b" />

- 스터디룸 생성, 수정, 삭제 기능을 제공하여 관리자가 손쉽게 스터디룸을 관리할 수 있는 기능

## 6. 트러블 슈팅
### 6-1. 좌석 예약 동시성 문제
- 문제 : A사용자와 B사용자가 같은 시간대에 같은 좌석을 조회하여 예약할 경우, 별도의 잠금이 없어 두 트랜잭션 모두 '비어 있음' 판정을 받고 각각 예약을 생성하는 동시성 문제 발생
- 해결 방법 : **비관적 락 방식**을 도입하여 조회 즉시 해당 행이 **강제 잠금이 되어 다른 트랜잭션의 접근을 차단한 상태**에서 예외 처리와 예약 저장을 진행하여 중복 차단
- 개선 효과 : 좌석 동시 예약 방지로 시스템 안정성 향상

### 6-2. 예약 취소 시 환급 시간 두 배 적용 문제
- 문제 : 환급 시간 함수가 두 번 적용되며 시간을 계산할 경우 시간 계산이 이뤄지지 않고 시간이 두배로 늘어남
- 해결 방법 : 기존에 예약 취소 시 미사용 시간만 환급하는 방식에서 **전체 예약 시간을 먼저 차감한 뒤 미사용 시간만을 환급**하도록 로직 변경
- 개선 효과 : 시간이 두 배 환급하는 문제를 방지하여 시스템 안정성을 향상

## 개선 목표
- 사용자의 웹 사용 이유 및 전국 스터디카페와 제휴를 맺는 이유를 명확화 및 그에 맞는 기능 추가

| 확장 범위                    | Description                                               |
|--------------------------|-----------------------------------------------------------|
| 광고 시청 기능                 | 사용자에게 광고 시청을 제공하여 이용권 구매에 도움이 되는 리워드 제공                   |
| 이용권 보상 로직                | 획득한 리워드를 통해 이용권을 보상 받을 수 있는 기능                            |
| 할인 기능                    | 신규 가입 회원에게 스터디룸의 할인권을 제공하여 사용자들을 모음                       |
| 홍보 채널 사용                 | 소규모의 스터디룸을 운영하는 사장님과 제휴를 맺어, 본인의 스터디룸을 홍보할 수 있음           |
| 운영 효율성 증대                | 좌석 예약 현황을 실시간으로 확인 가능하기에,피크 타임 분석과 예약 통계 확인 기능을 통해 효율성 증대 |
| AWS 배포 구성(EC2 + RDS + S3 | 클라우드 기반 인프라 구축 및 자원 관리                                    |




