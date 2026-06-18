package solo.EducationApp.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import solo.EducationApp.dto.request.lesson.LessonCreationRequest;
import solo.EducationApp.dto.request.lesson.LessonUpdateRequest;
import solo.EducationApp.entity.Lesson;
import solo.EducationApp.repository.LessonRepository;
import java.util.List;


@Service
public class LessonService {
    @Autowired
    LessonRepository lessonRepository;

    public String getSlug(String lessonName) {
        return lessonName.trim().replaceAll(" ", "-").toLowerCase();
    }

    public Lesson createLesson(LessonCreationRequest request) {
        Lesson lesson = Lesson.builder()
                .lessonName(request.getLessonName())
                .lessonSlug(getSlug(request.getLessonName()))
                .lessonPrice(request.getLessonPrice())
                .lessonQuantity(request.getLessonQuantity())
                .lessonDescription(request.getLessonDescription())
                .build();
        return lessonRepository.save(lesson);
    }

    public List<Lesson> getLessons() {
        return lessonRepository.findAll();
    }

    public Lesson getLesson(int id) {
        return lessonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lesson does not exist"));
    }

    public Lesson updateLesson (String lessonName, LessonUpdateRequest request) {
        Lesson lesson = Lesson.builder()
                .lessonName(lessonName)
                .lessonSlug(getSlug(lessonName))
                .lessonPrice(request.getLessonPrice())
                .lessonQuantity(request.getLessonQuantity())
                .lessonDescription(request.getLessonDescription())
                .build();
        return lessonRepository.save(lesson);
    }

    public void deleteLesson(String lessonName) {
        Lesson lesson = getLessonByName(lessonName);
        lessonRepository.delete(lesson);
    }

    public Lesson getLessonByName(String lessonName) {
        return lessonRepository.findByLessonName(lessonName)
                .orElseThrow(() -> new RuntimeException("Lesson does not exist"));
    }
}
