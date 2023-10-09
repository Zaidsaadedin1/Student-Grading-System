package com.example.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    public Long validateStudentLogin(Long id, String password) {
        Student student = studentRepository.findByIdAndPassword(id, password);
        if (student != null) {
            return student.getId();
        } else {
            return null; // Return null if no student found
        }
    }





}
