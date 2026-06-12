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


    @PutMapping("/{lessonName}")
    public  Lesson updateLesson(@PathVariable String lessonName, @RequestBody @Valid LessonUpdateRequest request) {
        return lessonService.updateLesson(lessonName, request);
    }

    @DeleteMapping("/{lessonName}")
    public void deleteLesson(@PathVariable String lessonName) {
        lessonService.deleteLesson(lessonName);
    }

    @GetMapping("/{lessonName}")
    public Lesson getLessonByName(@PathVariable String lessonName) {
        return lessonService.getLessonByName(lessonName);
    }
}
