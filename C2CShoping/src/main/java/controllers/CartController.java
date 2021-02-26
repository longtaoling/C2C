package controllers;

import DAO.CustomerDAO;
import DAO.ItemDAO;
import POJOs.CustomerEntity;
import POJOs.ItemEntity;
import View.OrderView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CartController {
    @Autowired
    HttpSession session;
    @Autowired
    ItemDAO itemDAO;
    @Autowired
    CustomerDAO customerDAO;
    @RequestMapping(value="/shoppingCart.htm", method= RequestMethod.GET)
    protected String cart() {
        return "shoppingCart";
    }
    @RequestMapping(value="/addToCart.htm", method= RequestMethod.GET)
    protected String addToCart(ModelMap model, int item, HttpServletResponse response) throws IOException {
        ItemEntity i=itemDAO.getItemById(item);
        if(session.getAttribute("user")==null) {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<script>alert(\"Please log in\")</script>");
            out.flush();
            model.addAttribute("item",i);
            model.addAttribute("type","buy");
            return "itemDetail";
        }else{
            CustomerEntity user=(CustomerEntity)session.getAttribute("user");
            user.getCart().add(i);
            customerDAO.updateCustomer(user);
        }
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.print("<script>alert(\"This item has been added to your cart\")</script>");
        out.flush();
        model.addAttribute("item",i);
        model.addAttribute("type","see");
        return "itemDetail";
    }
    @RequestMapping(value="/removeItemFromCart.htm", method= RequestMethod.GET)
    protected String removeItemFromCart(int item) {
        ItemEntity i=itemDAO.getItemById(item);
        CustomerEntity user=(CustomerEntity)session.getAttribute("user");
        user.getCart().remove(i);
        customerDAO.updateCustomer(user);
        CustomerEntity user1=customerDAO.getCustomer(user.getUsername());
        session.setAttribute("user",user1);
        return "shoppingCart";
    }
    @RequestMapping(value="/placeOrder.htm", method= RequestMethod.GET)
    protected OrderView placeOrder(ModelMap model) {
        CustomerEntity user=(CustomerEntity)session.getAttribute("user");
        List<ItemEntity> cart = new ArrayList<>(user.getCart());
        user.getCart().clear();
        customerDAO.updateCustomer(user);
        itemDAO.deleteItems(cart);
        model.addAttribute("cart",cart);
        return new OrderView();
    }
}
