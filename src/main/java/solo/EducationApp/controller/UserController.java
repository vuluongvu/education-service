package solo.EducationApp.controller;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import solo.EducationApp.dto.request.user.UserUpdateRequest;
import solo.EducationApp.entity.User;
import jakarta.validation.Valid;
import solo.EducationApp.dto.request.user.UserCreationRequest;
import solo.EducationApp.dto.response.ApiResponse;
import solo.EducationApp.service.UserService;

import java.util.List;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PUBLIC)
public class UserController {

    private final UserService userService;

    @PostMapping
    ApiResponse<User> createUser(@RequestBody @Valid UserCreationRequest request) {
        ApiResponse<User> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.createUser(request));
        return apiResponse;
    }

    @GetMapping
    ApiResponse<List<User>> getAllUsers () {
      ApiResponse<List<User>> apiResponses = new ApiResponse<>();
      apiResponses.setResult(userService.getAllUsers());
      return apiResponses;
    }

    @PutMapping("/{username}")
    ApiResponse<User> updateUser(@PathVariable String username, @Valid @RequestBody UserUpdateRequest request) {
        ApiResponse<User> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.updateUser(request, username));
        return apiResponse;
    }

    @GetMapping("/{username}")
    ApiResponse<User> getUser(@PathVariable String username){
        ApiResponse<User> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.getUser(username));
        return apiResponse;
    }

    @DeleteMapping("/{username}")
    ApiResponse<String> deleteUser(@PathVariable String username) {
        userService.deleteUser(username);
        ApiResponse<String> apiResponse = new ApiResponse<>();
        apiResponse.setMessage("User deleted successfully");
        return apiResponse;
    }


}
