package com.example.Grade;

import com.example.Student.Student;
import com.example.course.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeService {
    @Autowired
    private GradeRepository gradeRepository;

    public List<Grade> findGradesByStudentId(Long studentId) {
        List<Grade> grades = gradeRepository.findGradesByStudentId(studentId);
        if (grades != null) {
            return grades;
        } else {
            return null;
        }
    }
}
