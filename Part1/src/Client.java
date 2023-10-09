import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try {
            while (true) {
            System.out.println("Client started");
            Socket socket = new Socket("localhost", 9806);

            BufferedReader bufferedReaderOut = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                System.out.println("====== Login Form ======");
                System.out.println("Enter your email");
                String studentEmail = bufferedReaderOut.readLine();
                out.println(studentEmail);

                System.out.println("Enter your password");
                String studentPassword = bufferedReaderOut.readLine();
                out.println(studentPassword);

                // Receive the student grades from the server
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String studentGrades = in.readLine();

                if (studentGrades != null) {
                    System.out.println("Login successful! Your grades: \n " + studentGrades +"\n");
                } else {
                    System.out.println("Login failed. Incorrect username or password. Please try again.\n\n\n");
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}