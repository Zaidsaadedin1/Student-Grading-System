package com.example.admin;

import com.example.Grade.Grade;
import com.example.Grade.GradeRepository;
import com.example.Student.Student;
import com.example.Student.StudentRepository;
import com.example.course.Course;
import com.example.course.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private GradeRepository gradeRepository;

    @Autowired
    private StudentRepository studentRepository;
    public Long validateStudentLogin(Long id, String password) {
        Admin admin = adminRepository.findByIdAndPassword(id, password);
        if (admin != null) {
            return admin.getId();
        } else {
            return null;
        }
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }


    public List<Grade> getAllGrades() {
        return gradeRepository.findAll();
    }
    public List<Student> showAllStudents() {
        return studentRepository.findAll();
    }

    public void addStudent(Student student) {
        studentRepository.save(student);
    }


    public void removeStudent(Long studentId) {
        studentRepository.deleteById(studentId);
    }



    public void addCourseToStudent(Long studentId, Long courseId) {
        Student student = studentRepository.findById(studentId).orElse(null);
        Course course = courseRepository.findById(courseId).orElse(null);

        if (student != null && course != null) {
            Grade grade = new Grade();
            grade.setStudent(student);
            grade.setCourse(course);
            gradeRepository.save(grade);
        }
    }
    public void removeCourseFromStudent(Long studentId, Long courseId) {
        Student student = studentRepository.findById(studentId).orElse(null);
        Course course = courseRepository.findById(courseId).orElse(null);

        if (student != null && course != null) {
            Grade grade = new Grade();
            grade.setStudent(student);
            grade.setCourse(course);
            gradeRepository.delete(grade);
        }
    }

    public void addCourse(String courseName) {
        Course course = new Course();
        course.setCourseName(courseName);
        courseRepository.save(course);
    }

    public void addGradeToStudent(Long studentId, Long courseId, double gradeValue) {
        Student student = studentRepository.findById(studentId).orElse(null);
        Course course = courseRepository.findById(courseId).orElse(null);

        if (student != null && course != null) {
            Grade grade = new Grade();
            grade.setStudent(student);
            grade.setCourse(course);
            grade.setMark(gradeValue);
            gradeRepository.save(grade);
        }
    }

}
