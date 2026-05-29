package solo.EducationApp.controller;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import solo.EducationApp.dto.request.lesson.LessonCreationRequest;
import solo.EducationApp.dto.request.lesson.LessonUpdateRequest;
import solo.EducationApp.entity.Lesson;
import solo.EducationApp.service.LessonService;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class LessonController {
    @Autowired
    LessonService lessonService;

    @PostMapping
    public Lesson createLesson(@RequestBody @Valid LessonCreationRequest request) {
        return lessonService.createLesson(request);
    }


    @GetMapping
    List<Lesson> getAllLessons() {
        return lessonService.getLessons();
    }

    @GetMapping("/{id}")
    public Lesson getLessonById(@PathVariable int id) {
        return lessonService.getLesson(id);
    }

    @PutMapping("/{id}")
    public  Lesson updateLesson(@PathVariable int id, @RequestBody @Valid LessonUpdateRequest request) {
        return lessonService.updateLesson(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteLesson(@PathVariable int id) {
        lessonService.deleteLesson(id);
    }

    @GetMapping("/{lessonName}")
    public Lesson getLessonByName(@PathVariable String lessonName) {
        return lessonService.getLessonByName(lessonName);
    }
}
