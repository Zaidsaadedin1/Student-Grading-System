import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Server  {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(9806);
            System.out.println("Server started. Listening on port 9806");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket.getInetAddress());

                BufferedReader bufferedReaderIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String serverReadEmail = bufferedReaderIn.readLine();
                String serverReadPassword = bufferedReaderIn.readLine();

                List<Map<String, String>> studentGrades = getStudentGrades(serverReadEmail, serverReadPassword);
                if (studentGrades != null) {
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                    out.println(studentGrades);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<Map<String, String>> getStudentGrades(String email, String password) throws SQLException {
        int studentId = getStudentId(email, password);

        if (studentId == -1) {
            System.out.println("Login failed. Incorrect username or password.");
            return null;
        }

        List<Map<String, String>> gradesList = new ArrayList<>();

        try (Connection conn = DatabaseManager.getConnection()) {
            String query = "SELECT * FROM grades WHERE student_id=?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, studentId);
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        Map<String, String> gradeRow = new HashMap<>();
                        for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                            String columnName = rs.getMetaData().getColumnName(i);
                            String columnValue = rs.getString(i);
                            gradeRow.put(columnName, columnValue);
                        }
                        gradesList.add(gradeRow);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return gradesList;
    }




    private static int getStudentId(String email, String password) throws SQLException {
        int id = -1;

        try (Connection conn = DatabaseManager.getConnection()) {
            String query = "SELECT id FROM students WHERE email = ? AND password = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, email);
                stmt.setString(2, password);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        id = rs.getInt("id");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }


}