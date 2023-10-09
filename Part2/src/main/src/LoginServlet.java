import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")  // Corrected URL pattern
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String password = request.getParameter("password");

        if (StudentDAO.validateStudent(id, password)) {
            Map<String, Integer> courseMarks = StudentDAO.getStudentCourseMarks(id);
            int highestMark = StudentDAO.getHighestMark(id);
            int minimumMark = StudentDAO.getMinimumMark(id);
            double averageMark = StudentDAO.getAverageMark(id);
            double medianMark = StudentDAO.getMedianMark(id);

            request.setAttribute("courseMarks", courseMarks);
            request.setAttribute("highestMark", highestMark);
            request.setAttribute("minimumMark", minimumMark);
            request.setAttribute("averageMark", averageMark);
            request.setAttribute("medianMark", medianMark);
            request.setAttribute("id", id);  // Set student ID attribute

            request.getRequestDispatcher("/loginAction.jsp").forward(request, response);  // Corrected JSP filename
        } else {
            request.setAttribute("message", "Login failed. Please check your ID and password.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }


}
