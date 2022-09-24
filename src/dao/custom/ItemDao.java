package dao.custom;

import dao.CrudDao;
import entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemDao extends CrudDao<Item, String> {
}
