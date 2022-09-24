package dao.custom;

import entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerDao {
    public boolean saveCustomer(Customer c) throws SQLException, ClassNotFoundException;
    public ArrayList<Customer> loadAllCustomers() throws SQLException, ClassNotFoundException;
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException;
    public boolean updateCustomer(Customer c) throws SQLException, ClassNotFoundException;
}
