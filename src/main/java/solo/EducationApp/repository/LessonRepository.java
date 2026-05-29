package solo.EducationApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import solo.EducationApp.entity.Lesson;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Integer> {
    public Lesson findByLessonName(String lessonName);
}
