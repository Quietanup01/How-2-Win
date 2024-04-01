package employee;


import java.sql.*;

public class Conn {
    
    Connection c;
    Statement s;

    public Conn () {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql:///employeemanagementsystem", "root", "Anup@0987");
            s = c.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
        }
    }
}