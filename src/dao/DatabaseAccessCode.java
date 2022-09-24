package dao;

import db.DBConnection;
import entity.Customer;
import entity.Item;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseAccessCode {
// manage Customers
    public boolean saveCustomer(Customer c) throws ClassNotFoundException, SQLException {
        return CrudUtil.execute("INSERT INTO Customer VALUES(?,?,?,?)",
                c.getId(),c.getName(),c.getAddress(),c.getSalary());
    }
    public ArrayList<Customer> loadAllCustomers() throws ClassNotFoundException, SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM Customer");
        ArrayList<Customer> list= new ArrayList<>();
        while (resultSet.next()){
            list.add(new Customer(resultSet.getString(1),resultSet.getString(2),
                    resultSet.getString(3),resultSet.getDouble(4)));
        }
        return list;
    }
    public boolean deleteCustomer(String id) throws ClassNotFoundException, SQLException {
        return CrudUtil.execute("DELETE FROM Customer WHERE id=?",id);
    }
    public boolean updateCustomer(Customer c) throws ClassNotFoundException, SQLException {
        return CrudUtil.execute("UPDATE Customer SET name=?,address=?,salary=? WHERE id=?",
                c.getName(),c.getAddress(),c.getSalary(),c.getId());
    }

// manage items
    public boolean saveItem(Item i) throws ClassNotFoundException, SQLException {
    return CrudUtil.execute("INSERT INTO Item VALUES(?,?,?,?)",
            i.getCode(),i.getDescription(),i.getUnitPrice(),i.getQtyOnHand());
}
    public ArrayList<Item> loadAllItems() throws ClassNotFoundException, SQLException {

        ResultSet resultSet = CrudUtil.execute("SELECT * FROM Item");
        ArrayList<Item> list= new ArrayList<>();
        while (resultSet.next()){
            list.add(new Item(resultSet.getString(1),resultSet.getString(2),
                    resultSet.getDouble(3),resultSet.getInt(4)));
        }
        return list;
    }
    public boolean deleteItem(String id) throws ClassNotFoundException, SQLException {
        return CrudUtil.execute("DELETE FROM Item WHERE code=?",id);
    }
    public boolean updateItem(Item i) throws ClassNotFoundException, SQLException {
        return CrudUtil.execute("UPDATE Item SET description=?,unitPrice=?,qtyOnHand=? WHERE code=?",
                i.getDescription(),i.getUnitPrice(),i.getQtyOnHand(),i.getCode());
    }


}
