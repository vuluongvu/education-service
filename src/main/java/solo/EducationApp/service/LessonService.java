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

    public Lesson createLesson(LessonCreationRequest request) {
        String lessonName = request.getLessonName().trim().replaceAll(" ", "-");
        Lesson lesson = Lesson.builder()
                .lessonName(lessonName)
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

    public Lesson updateLesson (int id, LessonUpdateRequest request) {
        Lesson lesson = getLesson(id);
        String lessonName = request.getLessonName().trim().replaceAll(" ", "-");

        lesson.setLessonName(lessonName);
        lesson.setLessonPrice(request.getLessonPrice());
        lesson.setLessonQuantity(request.getLessonQuantity());
        lesson.setLessonDescription(request.getLessonDescription());
        return lessonRepository.save(lesson);
    }

    public void deleteLesson(int id) {
        Lesson lesson = getLesson(id);
        lessonRepository.delete(lesson);
    }

    public Lesson getLessonByName(String lessonName) {
        return lessonRepository.findByLessonName(lessonName);
    }
}
