package controllers;

import DAO.CustomerDAO;
import POJOs.CustomerEntity;
import org.jasypt.hibernate5.encryptor.HibernatePBEStringEncryptor;
import org.jasypt.util.text.BasicTextEncryptor;
import org.jasypt.util.text.StrongTextEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
public class LoginController {
    @Autowired
    CustomerDAO customerDAO;
    @Autowired
    HttpSession session;
    @RequestMapping(value="/main.htm", method= RequestMethod.GET)
    protected String mainPage() {
        return "main";
    }
    @RequestMapping(value="/login.htm", method= RequestMethod.GET)
    protected String loginPage(){
        if((CustomerEntity)session.getAttribute("user")==null)
           return "login";
        else return "main";
    }
    @RequestMapping(value="/login.htm", method= RequestMethod.POST)
    protected String login(String username,String password,HttpServletResponse response,HttpServletRequest request) throws IOException {
        if(request.getAttribute("unsafe_request") == "true"){
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.print("<script>alert(\"Unsafe Request\")</script>");
            out.flush();
            return "login";
        }
        CustomerEntity c=customerDAO.getCustomer(username);
        if(customerDAO.checkPW(c, password)) {
            session.setAttribute("user", c);
            return "main";
        }
        else {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.print("<script>alert(\"Username or Password is wrong\")</script>");
            out.flush();
            return "login";
        }
    }
    @RequestMapping(value="/signup.htm", method= RequestMethod.GET)
    protected String signUpPage() {
        return "signup";
    }
    @RequestMapping(value="/signup.htm", method= RequestMethod.POST)
    protected String signUpPage(@Valid CustomerEntity customer,BindingResult result,String rePw, HttpServletResponse response,HttpServletRequest request) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        if(request.getAttribute("unsafe_request")=="true"){
            out.print("<script>alert(\"Unsafe Request\")</script>");
            out.flush();
            return "signup";
        }
        if(result.hasErrors()){
            List<ObjectError> errors=result.getAllErrors();
            for(ObjectError o:errors){
                out.print("<script>alert(\""+o.getDefaultMessage()+"\")</script>");
                out.flush();
                return "signup";
            }
        }
        if(customerDAO.getCustomer(customer.getUsername())!=null){
            out.print("<script>alert(\"This Username has been used\")</script>");
            out.flush();
            return "signup";
        }
        if(customer.getPassword().equals(rePw)) {
            customerDAO.addCustomer(customer);
            session.setAttribute("user", customerDAO.getCustomer(customer.getUsername()));
            return "main";
        }
            else {
            out.print("<script>alert(\"Passwords must match\")</script>");
            out.flush();
            return "signup";
        }
    }
    @RequestMapping(value="/logout.htm", method= RequestMethod.GET)
    protected String logout() {
        session.removeAttribute("user");
        return "main";
    }
}
