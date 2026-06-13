package solo.EducationApp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import solo.EducationApp.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    @Autowired
    private UserRepository userRepository;

    // todo
     public boolean authenticate(String username, String password) {
        var user = userRepository.findByUsername(username);
        if (user == null) return false;
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        return passwordEncoder.matches(password, user.getPassword());
    }
}
