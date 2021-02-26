package DAO;


import POJOs.ItemEntity;
import org.hibernate.query.Query;

import java.util.List;

public class ItemDAO extends DAO{
    public void addItem(ItemEntity item){
        begin();
        getSession().save(item);
        commit();
        close();
        System.out.println("add successful");
    }
    public void updateItem(ItemEntity item){
        begin();
        getSession().update(item);
        commit();
        close();
        System.out.println("update successful");
    }
    public void removeItem(String itemID){
        begin();
        ItemEntity item= getSession().get(ItemEntity.class,Integer.parseInt(itemID));
        getSession().delete(item);
        commit();
        close();
        System.out.println("remove successful");
    }
    public List getAllItems(){
        begin();
        String hql="FROM ItemEntity";
        List list=getSession().createQuery(hql).list();
        commit();
        close();
        return list;
    }
    public ItemEntity getItemById(int itemID){
        begin();
        ItemEntity i= getSession().get(ItemEntity.class,itemID);
        commit();
        close();
        return i;
    }

    public void deleteItems(List<ItemEntity> items){
        begin();
        for(ItemEntity item:items) {
            getSession().delete(item);
        }
        commit();
        close();
    }
    public List searchByKeyWord(String keyWord){
        begin();
        String hql="FROM ItemEntity WHERE itemName like:keyWord";
        Query q = getSession().createQuery(hql);
        q.setParameter("keyWord","%"+keyWord+"%");
        List list=q.list();
        commit();
        close();
        return list;
    }
    public List searchByType(String type){
        begin();
        String hql="FROM ItemEntity WHERE type =:type";
        Query q = getSession().createQuery(hql);
        q.setParameter("type",type);
        List list=q.list();
        commit();
        close();
        return list;
    }
}
