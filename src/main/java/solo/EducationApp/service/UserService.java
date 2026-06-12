package solo.EducationApp.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import solo.EducationApp.dto.request.user.UserCreationRequest;
import solo.EducationApp.dto.request.user.UserUpdateRequest;
import solo.EducationApp.entity.User;
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
        User user = getUser(username);
        if (user == null) throw new RuntimeException("User does not exist");
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
        return userRepository.findByUsername(username);
    }

    public void deleteUser(String username) {
        if (getUser(username) != null) {
            User user = getUser(username);
            userRepository.delete(user);
        }
    }
}
