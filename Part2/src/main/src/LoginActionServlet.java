import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;

@WebServlet("/loginAction")
public class LoginActionServlet extends HttpServlet {
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

        } else {
            request.setAttribute("courseMarks", Collections.EMPTY_MAP);
            request.setAttribute("highestMark", "Ssss");
            request.setAttribute("minimumMark","Ssss");
            request.setAttribute("averageMark","Ssss");
            request.setAttribute("medianMark", "Ssss");
            request.setAttribute("id", id);  // Set student ID attribute
        }
            request.getRequestDispatcher("/loginAction.jsp").forward(request, response);
    }
}
