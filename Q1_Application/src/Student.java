import java.sql.*;

public class Student{

    /*
        Function to retrieve all students from the database
        Establish database connection using try-with-resources
        Create a statement object for executing SQL queries
        Execute SQL query to select all students
        Iterate over the result set
        Print student details
        Print stack trace if an SQL exception occurs
    */
    public static void getAllStudents() {
        try (Connection connection = DatabaseConnection.connect();
             Statement y = connection.createStatement();
             ResultSet x = y.executeQuery("SELECT * FROM students")) {
            while (x.next()) {
                System.out.println(x.getInt("student_id") + ", "
                                    + x.getString("first_name") + ", "
                                    + x.getString("last_name") + ", "
                                    + x.getString("email") + ", "
                                    + x.getDate("enrollment_date"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
        Function to add a new student to the database
        Establish database connection using try-with-resources
        Prepare SQL statement for inserting new student data
        Set first_name, last_name, email, enrollment_date parameters
        Execute SQL statement and get the number of rows affected
        Check if any rows were inserted successfully
        Print success message
        Print stack trace if an SQL exception occurs
     */
    public static void addStudent(String firstName, String lastName, String email, Date enrollmentDate) {
        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement y = connection.prepareStatement(
                     "INSERT INTO students (first_name, last_name, email, enrollment_date) VALUES (?, ?, ?, ?)")) {

            y.setString(1, firstName);
            y.setString(2, lastName);
            y.setString(3, email);
            y.setDate(4, enrollmentDate);

            int x = y.executeUpdate();
            if (x > 0) {
                System.out.println("Added successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
        Function to update a student's email
        Establish database connection using try-with-resources
        Prepare SQL statement for updating student email
        Set new email parameter
        Set student ID parameter
        Execute SQL statement and get the number of rows affected
        Check if any rows were updated successfully
        Print success message
        Print stack trace if an SQL exception occurs
     */
    public static void updateStudentEmail(int studentId, String newEmail) {
        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement y = connection.prepareStatement(
                     "UPDATE students SET email = ? WHERE student_id = ?")) {

            y.setString(1, newEmail);
            y.setInt(2, studentId);

            int x = y.executeUpdate();
            if (x > 0) {
                System.out.println("Email updated successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
        Function to delete a student from the database
        Establish database connection using try-with-resources
        Prepare SQL statement for deleting student by ID
        Set student ID parameter
        Execute SQL statement and get the number of rows affected
        Check if any rows were deleted successfully
        Print success message
        Print stack trace if an SQL exception occurs
     */
    public static void deleteStudent(int studentId) {
        try (Connection connection = DatabaseConnection. connect();
             PreparedStatement y = connection.prepareStatement(
                     "DELETE FROM students WHERE student_id = ?")) {

            y.setInt(1, studentId);

            int x = y.executeUpdate();
            if (x > 0) {
                System.out.println("Deleted successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Main method for testing the application
    public static void main(String[] args) {

        System.out.println("All Students:");
        getAllStudents(); // Retrieve and print all students

        //addStudent("Libe", "Kiro", "lib.kiro@example.com", Date.valueOf("2024-03-17")); // Add a new student

        //updateStudentEmail(1, "lib.updated@gmail.com"); // Update email of a student

        //deleteStudent(1); // Delete a student

    }
}
