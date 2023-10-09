import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class StudentDAO {
    public static Map<String, Integer> courseMarks = new HashMap<>();
    public static boolean validateStudent(String id, String password) {
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM student WHERE id = ? AND password = ?")) {

            ps.setString(1, id);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static Map<String, Integer> getStudentCourseMarks(String id) {
        Map<String, Integer> gradeRow = new HashMap<>();
        try (Connection conn = DBUtil.getConnection()){

             PreparedStatement ps = conn.prepareStatement("SELECT course_id, marks FROM grades " +
                     "WHERE student_id = ?"); {

            ps.setString(1, id);

            try(ResultSet rs = ps.executeQuery()) {


                while (rs.next()) {
                    gradeRow.put(rs.getString("course_id"), rs.getInt("marks"));
                }
            }
        }
    } catch (SQLException e) {
            e.printStackTrace();
        }
        return gradeRow ;
    }

    public static int getHighestMark(String id) {
        int highestMark = 0;
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT MAX(marks) FROM grades WHERE student_id = ?")) {

            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                highestMark = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return highestMark;
    }

    public static int getMinimumMark(String id) {
        int minimumMark = 0;
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT MIN(marks) FROM grades WHERE student_id = ?")) {

            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                minimumMark = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return minimumMark;
    }

    public static double getAverageMark(String id) {
        double averageMark = 0;
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT AVG(marks) FROM grades WHERE  student_id = ?")) {

            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                averageMark = rs.getDouble(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return averageMark;
    }

    public static double getMedianMark(String id) {
        List<Integer> marks = new ArrayList<>();
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT marks FROM grades WHERE student_id = ?")) {

            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                marks.add(rs.getInt("marks"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Collections.sort(marks);
        int size = marks.size();
        if (size == 0 ) return 0;
        if (size % 2 == 0) {
           return marks.get(size / 2);


        } else {
            return marks.get(size / 2);
        }
    }
        public static void main(String[] args) {

            // Check if the map is empty
            if (courseMarks.isEmpty()) {
                System.out.println("The map is empty.");
            } else {
                System.out.println("The map is not empty.");
            }
        }
    }

