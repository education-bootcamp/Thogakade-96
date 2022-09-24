package dao;

import entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDao <T,ID>{
    public boolean save(T t) throws SQLException, ClassNotFoundException;
    public ArrayList<T> loadAll() throws SQLException, ClassNotFoundException;
    public boolean delete(ID id) throws SQLException, ClassNotFoundException;
    public boolean update(T t) throws SQLException, ClassNotFoundException;
}
