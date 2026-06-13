package solo.EducationApp.dto.response;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    public int statusCode = 1000;
    public String message = "Default Message";
    public T result;
}
