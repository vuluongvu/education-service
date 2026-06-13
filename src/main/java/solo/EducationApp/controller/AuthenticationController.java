package solo.EducationApp.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import solo.EducationApp.dto.request.Authentication.AuthenticationRequest;
import solo.EducationApp.dto.response.ApiResponse;
import solo.EducationApp.dto.response.AuthenticationResponse;
import solo.EducationApp.service.AuthenticationService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@FieldDefaults(level = AccessLevel.PUBLIC)
class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/log-in")
    ApiResponse<AuthenticationResponse> loginAuthentication(@RequestBody AuthenticationRequest request) {
        boolean result = authenticationService.authenticate(request.getUsername(), request.getPassword());

        AuthenticationResponse authenticationResponse = AuthenticationResponse.builder()
                .authenticated(result)
                .build();
        return ApiResponse.<AuthenticationResponse>builder()
                .result(authenticationResponse)
                .message("Authentication successful")
                .statusCode(1001)
                .build();
    }
}
