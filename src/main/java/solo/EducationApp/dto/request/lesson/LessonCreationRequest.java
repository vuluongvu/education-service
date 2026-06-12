package solo.EducationApp.dto.request.lesson;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LessonCreationRequest {
    @NotBlank
    private String lessonName;
    @NotBlank
    private double lessonPrice;
    @NotBlank
    private int lessonQuantity;
    @NotBlank
    private String lessonDescription;
}
