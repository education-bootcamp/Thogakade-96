package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.ItemDao;
import entity.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDaoImpl implements ItemDao {
    @Override
    public boolean saveItem(Item i) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO Item VALUES(?,?,?,?)",
                i.getCode(),i.getDescription(),i.getUnitPrice(),i.getQtyOnHand());
    }

    @Override
    public ArrayList<Item> loadAllItems() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM Item");
        ArrayList<Item> list= new ArrayList<>();
        while (resultSet.next()){
            list.add(new Item(resultSet.getString(1),resultSet.getString(2),
                    resultSet.getDouble(3),resultSet.getInt(4)));
        }
        return list;
    }

    @Override
    public boolean deleteItem(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM Item WHERE code=?",id);
    }

    @Override
    public boolean updateItem(Item i) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE Item SET description=?,unitPrice=?,qtyOnHand=? WHERE code=?",
                i.getDescription(),i.getUnitPrice(),i.getQtyOnHand(),i.getCode());
    }
}
