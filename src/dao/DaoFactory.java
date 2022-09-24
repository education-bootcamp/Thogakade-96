package dao;

import dao.custom.impl.CustomerDaoImpl;
import dao.custom.impl.ItemDaoImpl;

public class DaoFactory {
    private static DaoFactory daoFactory;
    private DaoFactory(){}
    public static DaoFactory getInstance(){
        return daoFactory==null?(daoFactory= new DaoFactory()):daoFactory;
    }
    public enum DaoType{
        ITEM,CUSTOMER
    }
    public <T> T getDao(DaoType type){
        switch (type){
            case CUSTOMER:
                return (T) new CustomerDaoImpl();
            case ITEM:
                return (T) new ItemDaoImpl();
            default:
                return null;
        }
    }
}
