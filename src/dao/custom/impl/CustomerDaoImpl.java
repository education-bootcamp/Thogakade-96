package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.CustomerDao;
import entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDaoImpl implements CustomerDao {
    @Override
    public boolean save(Customer c) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO Customer VALUES(?,?,?,?)",
                c.getId(),c.getName(),c.getAddress(),c.getSalary());
    }

    @Override
    public ArrayList<Customer> loadAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM Customer");
        ArrayList<Customer> list= new ArrayList<>();
        while (resultSet.next()){
            list.add(new Customer(resultSet.getString(1),resultSet.getString(2),
                    resultSet.getString(3),resultSet.getDouble(4)));
        }
        return list;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM Customer WHERE id=?",id);
    }

    @Override
    public boolean update(Customer c) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE Customer SET name=?,address=?,salary=? WHERE id=?",
                c.getName(),c.getAddress(),c.getSalary(),c.getId());
    }
}
