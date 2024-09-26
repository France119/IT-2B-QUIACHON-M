package testapp2B;

 import java.sql.Connection;
 import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
 import java.util.Scanner;

public class TestApp2B {
    
    //Connection Method to SQLITE
    public static Connection connectDB() {
            Connection con = null;
            try {
                Class.forName("org.sqlite.JDBC"); // Load the SQLite JDBC driver
                con = DriverManager.getConnection("jdbc:sqlite:tesrapp.db"); // Establish connection
                System.out.println("Connection Successful");
            } catch (Exception e) {
                System.out.println("Connection Failed: " + e);
            }
            return con;
        }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        connectDB();
        
        System.out.print("Enter First Name: ");
        String fname = sc.nextLine();
        System.out.print("Enter Last Name: ");
        String lname = sc.nextLine();
        System.out.print("Enter Email: ");
        String email = sc.next();
        System.out.print("Enter Status: ");
        String status = sc.next();
        
        String sql = "INSERT INTO tbl_Students (s_firstname, s_lastname, s_email, s_status) VALUES (?, ?, ?, ?)";  
        
        try{
            Connection con = connectDB();
           PreparedStatement pst = con.prepareStatement(sql);
            
              pst.setString(1, fname);
              pst.setString(2, lname);
              pst.setString(3, email);
              pst.setString(4, status);
              pst.executeUpdate();
            
            System.out.println("Insertion Successful!!.");
        }catch(SQLException ex){
            System.out.println("Connection Error: "+ ex.getMessage());
        }
    }
}