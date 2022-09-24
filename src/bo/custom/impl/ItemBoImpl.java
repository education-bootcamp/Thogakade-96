package bo.custom.impl;

import bo.custom.ItemBo;
import dao.DaoFactory;
import dao.custom.ItemDao;
import dto.CustomerDto;
import dto.ItemDto;
import entity.Customer;
import entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBoImpl implements ItemBo {

    private ItemDao dao = DaoFactory.getInstance().getDao(DaoFactory.DaoType.ITEM);

    @Override
    public boolean saveItem(ItemDto dto) throws SQLException, ClassNotFoundException {
        return dao.save(
                new Item(dto.getCode(),dto.getDescription(),dto.getUnitPrice(),dto.getQtyOnHand())
        );
    }

    @Override
    public boolean updateItem(ItemDto dto) throws SQLException, ClassNotFoundException {
        return dao.update(
                new Item(dto.getCode(),dto.getDescription(),dto.getUnitPrice(),dto.getQtyOnHand())
        );
    }

    @Override
    public boolean deleteItem(String id) throws SQLException, ClassNotFoundException {
        return dao.delete(id);
    }

    @Override
    public ArrayList<ItemDto> loadAllItems() throws SQLException, ClassNotFoundException {
        ArrayList<Item> items = dao.loadAll();
        ArrayList<ItemDto> dtoList= new ArrayList<>();
        for (Item i:items
        ) {
            dtoList.add(new ItemDto(i.getCode(),i.getDescription(),i.getUnitPrice(),i.getQtyOnHand()));
        }
        return dtoList;
    }
}
