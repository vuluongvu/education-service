package solo.EducationApp.dto.request.user;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCreationRequest {
    @Size(min = 8, max = 20)
    private String username;
    private String firstName;
    private String lastName;
    @Email
    private String email;
    @Size(min = 8, max = 20)
    private String password;
}
