package dao;

import entity.Customer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseAccessCode {
// save Customer
    public boolean saveCustomer(Customer c) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Thogakade",
                "root","1234");
        PreparedStatement statement = connection.prepareStatement("INSERT INTO Customer VALUES(?,?,?,?)");
        statement.setString(1,c.getId());
        statement.setString(2,c.getName());
        statement.setString(3,c.getAddress());
        statement.setDouble(4,c.getSalary());
        return statement.executeUpdate()>0;
    }
}
