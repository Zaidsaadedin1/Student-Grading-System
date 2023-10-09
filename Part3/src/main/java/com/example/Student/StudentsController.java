package com.example.Student;

import com.example.Grade.Grade;
import com.example.Grade.GradeRepository;
import com.example.Grade.GradeService;
import com.example.course.Course;
import com.example.course.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Set;

@Controller
public class StudentsController {
    @Autowired
    private GradeService gradeService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private CourseService courseService;
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String validateLogin(@RequestParam("id") Long id, @RequestParam("password") String password, HttpSession session) {
        Long studentId = studentService.validateStudentLogin(id, password);
        if (studentId != null) {
            session.setAttribute("studentId", studentId);
            return "StudentDashBoard";
        } else {
            return "login";
        }
    }

    @GetMapping("/StudentDashBoard")
    public String showDashboard(HttpSession session) {
        Long studentId = (Long) session.getAttribute("studentId");
            return "redirect:/StudentDashBoard";

    }


    @GetMapping("/Courses")
    public String showStudentCourses(HttpSession session, Model model) {
        Long studentId = (Long) session.getAttribute("studentId");
        if (studentId != null) {
            List<Course> courses = courseService.findCoursesByStudentId(studentId);
            model.addAttribute("courses", courses);
            return "courses";
        } else {
            return "redirect:/StudentDashBoard";
        }
    }


    @GetMapping("/Grades")
    public String showGrades(HttpSession session, Model model) {
        Long studentId = (Long) session.getAttribute("studentId");
        if (studentId != null) {
            List<Grade> grades = gradeService.findGradesByStudentId(studentId);
            model.addAttribute("grades", grades);
            return "Grades";
        } else {
            return "StudentDashBoard";
        }
    }

    @GetMapping("/logout")
    public String logOut(HttpServletRequest request) throws ServletException {
        request.logout(); // Invalidate the user's session
        return "home"; // Redirect to the login page
    }
}
