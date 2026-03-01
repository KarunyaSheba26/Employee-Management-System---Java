import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDAO {

    Connection con;

    public EmployeeDAO() {
        try {
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/employeedb",
                    "root",
                    "MySQLroot123");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addEmployee(Employee emp) {
        try {
            String query = "INSERT INTO employee (id, name, department, salary) VALUES (?, ?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, emp.getId());
            ps.setString(2, emp.getName());
            ps.setString(3, emp.getDepartment());
            ps.setDouble(4, emp.getSalary());

            ps.executeUpdate();
            System.out.println("Employee added successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewEmployees() {
        try {
            String query = "SELECT * FROM employee";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Department: " + rs.getString("department"));
                System.out.println("Salary: " + rs.getDouble("salary"));
                System.out.println("---------------------");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateEmployee(Employee emp) {
        try {
            String query = "UPDATE employee SET name=?, department=?, salary=? WHERE id=?";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, emp.getName());
            ps.setString(2, emp.getDepartment());
            ps.setDouble(3, emp.getSalary());
            ps.setInt(4, emp.getId());

            ps.executeUpdate();
            System.out.println("Employee updated successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteEmployee(int id) {
        try {
            String query = "DELETE FROM employee WHERE id=?";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);

            ps.executeUpdate();
            System.out.println("Employee deleted successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
