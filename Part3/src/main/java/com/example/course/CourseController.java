package com.example.course;

import com.example.Grade.Grade;
import com.example.Grade.GradeService;
import com.example.Student.Student;
import com.example.Student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/Courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

}
