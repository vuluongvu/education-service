package solo.EducationApp.dto.request.lesson;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LessonCreationRequest {
    private String lessonName;
    private double lessonPrice;
    private int lessonQuantity;
    private String lessonDescription;
}
