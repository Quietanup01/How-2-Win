package bank;

import java.sql.*;

public class Connn {
    Connection connection;
    Statement statement;
    public Connn(){
        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankSystem","root","Anup@19728");
            statement = connection.createStatement();
        }catch (Exception e){
            e.printStackTrace();
        }


    }
}