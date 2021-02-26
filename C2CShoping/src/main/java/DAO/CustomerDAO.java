package DAO;


import POJOs.CustomerEntity;
import org.hibernate.query.Query;

public class CustomerDAO extends DAO{

    public void addCustomer(CustomerEntity customer){

        begin();
        getSession().save(customer);
        commit();
        close();
        System.out.println("add successful");
    }
    public void updateCustomer(CustomerEntity customer){

        begin();
        getSession().update(customer);
        commit();
        close();
        System.out.println("add successful");
    }
    public boolean checkPW(CustomerEntity c,String password){
        if(c==null)
            return false;
        return c.getPassword().equals(password);
    }
    public CustomerEntity getCustomer(String username){
        begin();
        String hql="FROM CustomerEntity WHERE username=:username";
        Query q = getSession().createQuery(hql);
        q.setParameter("username",username);
        CustomerEntity c=(CustomerEntity) q.uniqueResult();
        commit();
        close();
        return c;
    }
}
