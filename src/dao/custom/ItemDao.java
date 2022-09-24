package dao.custom;

import entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemDao {
    public boolean saveItem(Item i) throws SQLException, ClassNotFoundException;
    public ArrayList<Item> loadAllItems() throws SQLException, ClassNotFoundException;
    public boolean deleteItem(String id) throws SQLException, ClassNotFoundException;
    public boolean updateItem(Item i) throws SQLException, ClassNotFoundException;
}
