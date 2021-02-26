package POJOs;

import org.hibernate.annotations.Proxy;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Proxy(lazy = false)
@Table(name = "customer", schema = "finalproject")
public class CustomerEntity {
    private int customerId;
    private String username;
    private String password;
    private String email;
    private String phoneNum;
    private List<ItemEntity> sellItems=new ArrayList<>();
    private List<ItemEntity> cart=new ArrayList<>();

    @Id
    @Column(name = "CustomerID", nullable = false)
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @Length(min=3,max=15,message = "Username length should be between 3 and 15")
    @Basic
    @Column(name = "Username", nullable = false, length = 15)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Length(min=6,max=15,message = "Username length should be between 6 and 15")
    @Basic
    @Column(name = "Password", nullable = false, length = 15)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Email(message="Email Format Error")
    @Basic
    @Column(name = "Email", nullable = false, length = 45)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NotBlank(message = "Phone Number should not be empty")
    @Basic
    @Column(name = "PhoneNum", nullable = false, length = 45)
    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomerEntity that = (CustomerEntity) o;

        if (customerId != that.customerId) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (phoneNum != null ? !phoneNum.equals(that.phoneNum) : that.phoneNum != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = customerId;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (phoneNum != null ? phoneNum.hashCode() : 0);
        return result;
    }
    @OneToMany(mappedBy ="customer",fetch=FetchType.EAGER)
    public List<ItemEntity> getSellItems() {
        return sellItems;
    }

    public void setSellItems(List<ItemEntity> sellItems) {
        this.sellItems = sellItems;
    }
    @ManyToMany(targetEntity = ItemEntity.class,fetch=FetchType.EAGER)
    @JoinTable(name= "shoppingCart",
            joinColumns = { @JoinColumn(name = "customerID", referencedColumnName = "customerID") },
            inverseJoinColumns = { @JoinColumn(name = "itemID", referencedColumnName = "itemID") })
    public List<ItemEntity> getCart() {
        return cart;
    }
    public void setCart(List<ItemEntity> cart) {
        this.cart = cart;
    }
}
