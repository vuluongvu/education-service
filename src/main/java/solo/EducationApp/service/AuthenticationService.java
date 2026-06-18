package solo.EducationApp.service;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import lombok.RequiredArgsConstructor;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import solo.EducationApp.dto.request.Authentication.AuthenticationRequest;
import solo.EducationApp.dto.response.AuthenticationResponse;
import solo.EducationApp.exception.AppException;
import solo.EducationApp.exception.ErrorCode;
import solo.EducationApp.repository.UserRepository;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {
    @Autowired
    private UserRepository userRepository;

    @NonFinal
    protected static final String SIGNER_KEY = "a23eba281a3b03b0216dbf6a2793b856c01b18ceca4c185dccffd2f030da81c7";
    // todo

     public AuthenticationResponse authenticate(AuthenticationRequest request) {
         var user = userRepository.findByUsername(request.getUsername())
                 .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        boolean authenticated =  passwordEncoder.matches(request.getPassword(), user.getPassword());

        if (!authenticated)
            throw  new UsernameNotFoundException("Invalid username or password");

        var token = generateToken(request.getUsername());
        return AuthenticationResponse.builder()
                .token(token)
                .authenticated(true)
                .build();
    }

    // generate token
    private String generateToken (String username){
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(username)
                .issuer("luongvanvu.com")
                .issueTime(new Date(System.currentTimeMillis()))
                .expirationTime(
                        new Date(
                                Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()
                        )
                )
                .claim("custom claim", "custom value")
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(header,payload);

        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return  jwsObject.serialize();
        } catch (JOSEException e){
            log.error("Failed to generate token", e);
            throw new RuntimeException(e);
        }
    }
}
