import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DBManager {
    private Connection connection;

    public void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/java_se_mini_project?useUnicode=true&serverTimezone=UTC",
                    "root", ""
            );
            System.out.println("CONNECTED to DATA BASE");
        } catch (Exception e) {
            System.out.println("NOT connected to DB!!!!");
            e.printStackTrace();
        }
    }

    public ArrayList<Students> getALLStudents() {
        ArrayList<Students> students = new ArrayList<Students>();
        try {
            PreparedStatement st = connection.prepareStatement("select * from students");

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                int age = rs.getInt("age");
                students.add(new Students(id, name, surname, age));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return students;
    }

    public void addStudent(Students student) {
        try {
            PreparedStatement st = connection.prepareStatement("INSERT INTO students(id, name, surname, age) values(NULL,?,?,?)");

            st.setString(1, student.getName());
            st.setString(2, student.getSurname());
            st.setInt(3, student.getAge());

            st.executeUpdate();

            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
