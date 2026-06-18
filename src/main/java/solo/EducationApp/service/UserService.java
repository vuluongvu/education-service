package solo.EducationApp.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import solo.EducationApp.dto.request.user.UserCreationRequest;
import solo.EducationApp.dto.request.user.UserUpdateRequest;
import solo.EducationApp.entity.User;
import solo.EducationApp.exception.AppException;
import solo.EducationApp.exception.ErrorCode;
import solo.EducationApp.repository.UserRepository;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(UserCreationRequest request) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .username(request.getUsername())
                .build();

        return userRepository.save(user);
    }

    public User updateUser(UserUpdateRequest request, String username) {
        var user = getUser(username);

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUser(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
    }

    public void deleteUser(String username) {
        var user = getUser(username);
        userRepository.delete(user);
        }
}
