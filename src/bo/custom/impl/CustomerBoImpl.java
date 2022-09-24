package bo.custom.impl;

import bo.custom.CustomerBo;
import dao.DaoFactory;
import dao.custom.CustomerDao;
import dto.CustomerDto;
import entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBoImpl implements CustomerBo {

    private CustomerDao dao
            = DaoFactory.getInstance().getDao(DaoFactory.DaoType.CUSTOMER);

    @Override
    public boolean saveCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException {
        return dao.save(new Customer(dto.getId(),
                dto.getName(), dto.getAddress(), dto.getSalary()));
    }

    @Override
    public boolean updateCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException {
        return dao.update(new Customer(dto.getId(),
                dto.getName(), dto.getAddress(), dto.getSalary()));
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        return dao.delete(id);
    }

    @Override
    public ArrayList<CustomerDto> loadAllCustomers() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> customers = dao.loadAll();
        ArrayList<CustomerDto> dtoList= new ArrayList<>();
        for (Customer c:customers
             ) {
            dtoList.add(new CustomerDto(c.getId(),c.getName(),c.getAddress(),c.getSalary()));
        }
        return dtoList;
    }
}
