package dao;

import entity.Customer;
import entity.Item;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseAccessCode {
// manage Customers
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
    public ArrayList<Customer> loadAllCustomers() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Thogakade",
                "root","1234");
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM Customer");
        ResultSet resultSet = statement.executeQuery();
        ArrayList<Customer> list= new ArrayList<>();
        while (resultSet.next()){
            list.add(new Customer(resultSet.getString(1),resultSet.getString(2),
                    resultSet.getString(3),resultSet.getDouble(4)));
        }
        return list;
    }
    public boolean deleteCustomer(String id) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Thogakade",
                "root","1234");
        PreparedStatement statement =
                connection.prepareStatement("DELETE FROM Customer WHERE id=?");
        statement.setString(1,id);
        return statement.executeUpdate()>0;
    }
    public boolean updateCustomer(Customer c) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Thogakade",
                "root","1234");
        PreparedStatement statement = connection
                .prepareStatement("UPDATE Customer SET name=?,address=?,salary=? WHERE id=?");
        statement.setString(1,c.getName());
        statement.setString(2,c.getAddress());
        statement.setDouble(3,c.getSalary());
        statement.setString(4,c.getId());
        return statement.executeUpdate()>0;
    }

// manage items
    public boolean saveItem(Item i) throws ClassNotFoundException, SQLException {
    Class.forName("com.mysql.cj.jdbc.Driver");
    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Thogakade",
            "root","1234");
    PreparedStatement statement = connection.prepareStatement("INSERT INTO Item VALUES(?,?,?,?)");
    statement.setString(1,i.getCode());
    statement.setString(2,i.getDescription());
    statement.setDouble(3,i.getUnitPrice());
    statement.setInt(4,i.getQtyOnHand());
    return statement.executeUpdate()>0;
}
}
