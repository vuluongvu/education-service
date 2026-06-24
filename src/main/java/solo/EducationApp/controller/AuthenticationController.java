package solo.EducationApp.controller;

import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import solo.EducationApp.dto.request.Authentication.AuthenticationRequest;
import solo.EducationApp.dto.request.VerifyTokenRequest.IntrospectRequest;
import solo.EducationApp.dto.response.ApiResponse;
import solo.EducationApp.dto.response.AuthenticationResponse;
import solo.EducationApp.dto.response.IntrospectResponse;
import solo.EducationApp.service.AuthenticationService;

import java.text.ParseException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@FieldDefaults(level = AccessLevel.PUBLIC)
class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/token")
    ApiResponse<AuthenticationResponse> loginAuthentication(@RequestBody AuthenticationRequest request) {
        var result = authenticationService.authenticate(request);

        return ApiResponse.<AuthenticationResponse>builder()
                .result(result)
                .message("Authentication successful")
                .statusCode(1001)
                .build();
    }

    @PostMapping("/introspect")
    ApiResponse<IntrospectResponse> authenticate (@RequestBody IntrospectRequest request) throws ParseException, JOSEException {
        var result = authenticationService.introspect(request);
        return ApiResponse.<IntrospectResponse>builder()
                .result(result)
                .message("Authentication successful")
                .statusCode(1001)
                .build();
    }
}
