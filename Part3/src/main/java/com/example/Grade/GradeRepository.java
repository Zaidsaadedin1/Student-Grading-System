package com.example.Grade;

import com.example.Student.Student;
import com.example.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeRepository extends JpaRepository<Grade,Long> {
    List<Grade> findGradesByStudentId(Long studentId);
    @Query("SELECT DISTINCT g.course FROM Grade g WHERE g.student.id = :studentId")
    List<Course> findCoursesByStudentId(@Param("studentId") Long studentId);
}
