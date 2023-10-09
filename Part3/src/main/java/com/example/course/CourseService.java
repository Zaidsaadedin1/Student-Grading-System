package com.example.course;

import com.example.Grade.Grade;
import com.example.Grade.GradeRepository;
import com.example.Student.Student;
import com.example.Student.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private GradeRepository gradeRepository;
    @Autowired
    private StudentRepository studentRepository;
    public List<Course> findCoursesByStudentId(Long studentId) {
        return gradeRepository.findCoursesByStudentId(studentId);
    }

}
