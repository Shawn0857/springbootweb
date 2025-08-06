package com.example.test.server;

import com.example.test.demos.web.entity.User;
import com.example.test.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@Transactional
//@RequiredArgsConstructor可以用這個寫就不須需要public UserService這麻煩東西或是拿掉UserRepository final寫@Autowired
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 註冊
    public User register(String phoneNumber, String password, String userName) {
        if (userRepository.findByPhoneNumber(phoneNumber).isPresent()) {
            throw new RuntimeException("該手機號碼已被註冊");
        }
        User user = new User();
        user.setPhoneNumber(phoneNumber);
        user.setPassword(passwordEncoder.encode(password));
        user.setUserName(userName);
        user.setRegistrationTime(LocalDateTime.now());
        return userRepository.save(user);
    }

    // 登入
    public User login(String phoneNumber, String password) {
        User user = userRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new RuntimeException("帳號不存在"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("密碼錯誤");
        }

        user.setLastLoginTime(LocalDateTime.now());
        return userRepository.save(user);
    }
}
