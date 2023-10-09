package com.example.Grade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/Grades")
public class GradeController {

    @Autowired
    private GradeService gradeService;


}
