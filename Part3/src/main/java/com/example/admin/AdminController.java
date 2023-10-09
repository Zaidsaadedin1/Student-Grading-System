    package com.example.admin;
    import com.example.Grade.Grade;
    import com.example.Student.Student;
    import com.example.course.Course;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.*;
    import org.springframework.web.servlet.ModelAndView;

    import javax.servlet.ServletException;
    import javax.servlet.http.HttpServletRequest;
    import javax.servlet.http.HttpSession;
    import java.util.Comparator;
    import java.util.List;
    import java.util.stream.Collectors;

    @Controller
    public class AdminController {

        @Autowired
        private AdminService adminService;
        @GetMapping("/AdminLogin")
        public String showAdminLoginForm() {
            return "AdminLogin";
        }
        @PostMapping("/AdminLogin")
        public String validateLogin(@RequestParam("id") Long id, @RequestParam("password") String password, HttpSession session) {
            Long adminId = adminService.validateStudentLogin(id, password);
            if (adminId != null) {
                session.setAttribute("adminId", adminId);
                return "AdminDashBoard";
            } else {
                return "AdminLogin";
            }
        }

        @GetMapping("/AdminDashBoard")
        public String showDashboard(HttpSession session) {
            Long adminId = (Long) session.getAttribute("adminId");
            return "redirect:/AdminDashBoard";

        }

        @GetMapping("/home")
        public String logOut(HttpServletRequest request) throws ServletException {
            request.logout();
            return "home";
        }

        @GetMapping("/ShowCourses")
        public String showCourses(Model model) {
            List<Course> courses = adminService.getAllCourses();
            model.addAttribute("courses", courses);
            return "ShowCourses";
        }

        @GetMapping("/ShowGrades")
        public String showGrades(Model model) {
            List<Grade> grades = adminService.getAllGrades();
            model.addAttribute("grades", grades);
            return "ShowGrades";
        }
        @GetMapping("/SortGradesByStudentId")
        public String sortGradesByStudentId(Model model) {
            List<Grade> grades = adminService.getAllGrades();
            grades.sort(Comparator.comparing(grade -> grade.getStudent().getId())); // Sorting by student ID
            model.addAttribute("grades", grades);
            return "ShowGrades"; // The Thymeleaf template to display the sorted grades
        }
        @GetMapping("/SortAndFilterGrades")
        public String sortAndFilterGrades(
                @RequestParam(value = "option", required = false) String sortingOption,
                @RequestParam(value = "studentId", required = false) Long studentIdFilter,
                Model model) {
            List<Grade> grades = adminService.getAllGrades();

            // Apply filtering if studentIdFilter is provided
            if (studentIdFilter != null) {
                grades = grades.stream()
                        .filter(grade -> grade.getStudent().getId().equals(studentIdFilter))
                        .collect(Collectors.toList());
            }

            // Apply sorting based on the selected option
            if (sortingOption != null) {
                switch (sortingOption) {
                    case "studentId":
                        grades.sort(Comparator.comparing(grade -> grade.getStudent().getId()));
                        break;
                    case "mark":
                        grades.sort(Comparator.comparing(Grade::getMark));
                        break;
                    case "courseId":
                        grades.sort(Comparator.comparing(grade -> grade.getCourse().getId()));
                        break;
                    // Add more cases for other sorting options if needed

                    default:
                        // Handle default sorting here
                        break;
                }
            }

            model.addAttribute("filteredGrades", grades);
            return "ShowGrades"; // The Thymeleaf template to display the sorted and filtered grades
        }

        @GetMapping("/showAllStudents")
        public String showAllStudents(Model model) {
            List<Student> students = adminService.showAllStudents();
            model.addAttribute("students", students);
            return "showAllStudents";
        }

        @PostMapping("/AddStudent")
        public String addStudent(@ModelAttribute Student student) {
            adminService.addStudent(student);
            return "redirect:/AddStudent";
        }
        @GetMapping("/AddStudent")
        public String showAddStudentForm() {
            return "AddStudent";
        }


        @PostMapping("/RemoveStudent")
        public String removeStudent(@RequestParam("id") Long studentId) {
            adminService.removeStudent(studentId);
            return "redirect:/RemoveStudent";
        }
        @GetMapping("/RemoveStudent")
        public String showRemoveStudentForm() {
            return "RemoveStudent";
        }


        @PostMapping("/AddCourseToStudent")
        public String addCourseToStudent(@RequestParam("studentId") Long studentId, @RequestParam("courseId") Long courseId) {
            adminService.addCourseToStudent(studentId, courseId);
            return "redirect:/AddCourseToStudent";
        }
        @GetMapping("/AddCourseToStudent")
        public String showAddCourseToStudentForm() {
            return "AddCourseToStudent";
        }


        @PostMapping("/RemoveCourseFromStudent")
        public String removeCourseFromStudent(@RequestParam("studentId") Long studentId, @RequestParam("courseId") Long courseId) {
            adminService.removeCourseFromStudent(studentId, courseId);
            return "redirect:/RemoveCourseFromStudent";
        }
        @GetMapping("/RemoveCourseFromStudent")
        public String showRemoveCourseFromStudentForm() {
            return "RemoveCourseFromStudent";
        }



        @PostMapping("/AddCourse")
        public String addCourse(@RequestParam("courseName") String courseName) {
            adminService.addCourse(courseName);
            return "redirect:/AddCourse";
        }
        @GetMapping("/AddCourse")
        public String showAddCourseForm() {
            return "AddCourse";
        }


        @PostMapping("/AddGradeToStudent")
        public String addGradeToStudent(
                @RequestParam Long studentId,
                @RequestParam Long courseId,
                @RequestParam double mark) {
            adminService.addGradeToStudent(studentId, courseId, mark);
            return "redirect:/AddGradeToStudent";
        }

        @GetMapping("/AddGradeToStudent")
        public String showAddGradeToStudentForm() {
            return "AddGradeToStudent";
        }


    }
