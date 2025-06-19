//package me.minkyoung.stoq_back.config;
//
//import lombok.RequiredArgsConstructor;
//import me.minkyoung.stoq_back.entity.User;
//import me.minkyoung.stoq_back.repository.UserRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//
//@Component
//@RequiredArgsConstructor
//public class AdminInitializer implements CommandLineRunner {
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//
//
//    public void run(String... args) throws Exception {
//        String adminEmail = "admin@example.com";
//
//        if(userRepository.findByEmail(adminEmail).isEmpty()){
//           User admin = User.builder()
//                   .email(adminEmail)
//                   .password(passwordEncoder.encode("1234"))
//                   .name("관리자")
//                   .phone("000-0000-0000")
//                   .isMember(true)
//                   .isAdmin(true)
//                   .remaining_time(100000)
//                   .build();
//           userRepository.save(admin);
//           System.out.println("관리자 계정 생성:" + adminEmail);
//        }
//    }
//}
