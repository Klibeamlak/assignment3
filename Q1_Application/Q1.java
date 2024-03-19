import java.sql.*;

public Student{
    //Database connection parameters
    private static final String URL = "jdbc:postgresql://localhost:5432/assignment3"; 
    private static final String Username  = "postgres";
    private static final String Password  = "Thelibe11"; 

    //A function connect to database
    public static Connection connect(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, Username, Password); // Establish connection to the database
        } catch (SQLException e) {
            e.printStackTrace(); // Print stack trace if connection fails
        }
        return connection; // Return the established connection (or null if failed)
    }

public static void getAllStudents() {
    try (Connection connection = connect(); // Establish database connection using try-with-resources
         Statement statement = connection.createStatement(); // Create a statement object for executing SQL queries
         ResultSet resultSet = statement.executeQuery("SELECT * FROM students")) { // Execute SQL query to select all students

        while (resultSet.next()) { // Iterate over the result set
            int studentId = resultSet.getInt("student_id"); // Get student ID from the result set
            String firstName = resultSet.getString("first_name"); // Get first name from the result set
            String lastName = resultSet.getString("last_name"); // Get last name from the result set
            String email = resultSet.getString("email"); // Get email from the result set
            Date enrollmentDate = resultSet.getDate("enrollment_date"); // Get enrollment date from the result set

            // Print student details
            System.out.println("Student ID: " + studentId +
                    ", Name: " + firstName + " " + lastName +
                    ", Email: " + email +
                    ", Enrollment Date: " + enrollmentDate);
        }
    } catch (SQLException e) {
        e.printStackTrace(); // Print stack trace if an SQL exception occurs
    }
}
// Function to add a new student to the database
public static void addStudent(String firstName, String lastName, String email, Date enrollmentDate) {
    try (Connection connection = connect(); // Establish database connection using try-with-resources
         PreparedStatement preparedStatement = connection.prepareStatement(
                 "INSERT INTO students (first_name, last_name, email, enrollment_date) VALUES (?, ?, ?, ?)")) { // Prepare SQL statement for inserting new student data

        preparedStatement.setString(1, firstName); // Set first name parameter
        preparedStatement.setString(2, lastName); // Set last name parameter
        preparedStatement.setString(3, email); // Set email parameter
        preparedStatement.setDate(4, enrollmentDate); // Set enrollment date parameter

        int rowsInserted = preparedStatement.executeUpdate(); // Execute SQL statement and get the number of rows affected
        if (rowsInserted > 0) { // Check if any rows were inserted successfully
            System.out.println("Student added successfully!"); // Print success message
        }
    } catch (SQLException e) {
        e.printStackTrace(); // Print stack trace if an SQL exception occurs
    }
}
// Function to update a student's email
public static void updateStudentEmail(int studentId, String newEmail) {
    try (Connection connection = connect(); // Establish database connection using try-with-resources
         PreparedStatement preparedStatement = connection.prepareStatement(
                 "UPDATE students SET email = ? WHERE student_id = ?")) { // Prepare SQL statement for updating student email

        preparedStatement.setString(1, newEmail); // Set new email parameter
        preparedStatement.setInt(2, studentId); // Set student ID parameter

        int rowsUpdated = preparedStatement.executeUpdate(); // Execute SQL statement and get the number of rows affected
        if (rowsUpdated > 0) { // Check if any rows were updated successfully
            System.out.println("Student email updated successfully!"); // Print success message
        }
    } catch (SQLException e) {
        e.printStackTrace(); // Print stack trace if an SQL exception occurs
    }
}
 // Function to delete a student from the database
 public static void deleteStudent(int studentId) {
    try (Connection connection = connect(); // Establish database connection using try-with-resources
         PreparedStatement preparedStatement = connection.prepareStatement(
                 "DELETE FROM students WHERE student_id = ?")) { // Prepare SQL statement for deleting student by ID

        preparedStatement.setInt(1, studentId); // Set student ID parameter

        int rowsDeleted = preparedStatement.executeUpdate(); // Execute SQL statement and get the number of rows affected
        if (rowsDeleted > 0) { // Check if any rows were deleted successfully
            System.out.println("Student deleted successfully!"); // Print success message
        }
    } catch (SQLException e) {
        e.printStackTrace(); // Print stack trace if an SQL exception occurs
    }
}
 // Main method for testing the application
 public static void main(String[] args) {
    // Example usage of functions
    System.out.println("All Students:");
    getAllStudents(); // Retrieve and print all students

    //addStudent("Alice", "Johnson", "alice.johnson@example.com", Date.valueOf("2024-03-17")); // Add a new student

    //updateStudentEmail(1, "john.doe.updated@example.com"); // Update email of a student

    //deleteStudent(3); // Delete a student
    }
}