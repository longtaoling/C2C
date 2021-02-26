package controllers;

import DAO.CustomerDAO;
import DAO.ItemDAO;
import POJOs.CustomerEntity;
import POJOs.ItemEntity;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


@Controller
public class ItemController {
    @Autowired
    HttpSession session;
    @Autowired
    ItemDAO itemDAO;
    @Autowired
    CustomerDAO customerDAO;
    @RequestMapping(value="/sell.htm", method= RequestMethod.GET)
    protected String sellPage(HttpServletResponse response,ModelMap model) throws IOException {
        if(session.getAttribute("user")==null) {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<script  language='javascript'>alert(\"Please log in\")</script>");
            out.flush();
            model.addAttribute("allItem",itemDAO.getAllItems());
            return "item";
        }else {
            return "sell";
        }
    }
    @RequestMapping(value="/sell.htm", method= RequestMethod.POST)
    protected String sell(@Valid ItemEntity item,BindingResult result,@RequestParam(value = "pic", required = true) MultipartFile multipartFile, HttpServletResponse response, HttpServletRequest request) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        if(request.getAttribute("unsafe_request")=="true"){
            out.print("<script>alert(\"Unsafe Request\")</script>");
            out.flush();
            return "sell";
        }
        if(result.hasErrors()){
            List<ObjectError> errors=result.getAllErrors();
            for(ObjectError o:errors){
                out.print("<script>alert(\""+o.getDefaultMessage()+"\")</script>");
                out.flush();
                return "sell";
            }
        }
        String filename = null;
        if (!multipartFile.isEmpty()) {
            String path = request.getSession().getServletContext().getRealPath("WEB-INF/img" + File.separator + "ItemImg");
            String oldFileName = multipartFile.getOriginalFilename();
            String suffix = FilenameUtils.getExtension(oldFileName);
            long size = multipartFile.getSize();
            if (size > 454000) {
                out.print("<script>alert(\"Picture size cannot exceed 500kb\")</script>");
                out.flush();
                return null;
            } else if (suffix.equalsIgnoreCase("jpg") ||
                    suffix.equalsIgnoreCase("png") ||
                    suffix.equalsIgnoreCase("jpeg")){
                String NewFileName = System.currentTimeMillis() + RandomUtils.nextInt(100000) + "." + suffix;
                File targetFile = new File(path, NewFileName);
                if (!targetFile.exists()) {
                    targetFile.mkdirs();
                }
                try {
                    multipartFile.transferTo(targetFile);
                } catch (IOException e) {
                    e.printStackTrace();
                    out.print("<script>alert(\"Upload Picture Error\")</script>");
                    out.flush();
                    return null;
                }
                filename = NewFileName;
            }
            else{
                out.print("<script>alert(\"Picture format error\")</script>");
                out.flush();
                return null;
            }
        }
        item.setPicture(filename);
        CustomerEntity c=(CustomerEntity)session.getAttribute("user");
        item.setCustomer(c);
        itemDAO.addItem(item);
        CustomerEntity user=(CustomerEntity)session.getAttribute("user");
        CustomerEntity user1=customerDAO.getCustomer(user.getUsername());
        session.setAttribute("user",user1);
        out.print("<script>alert(\"Add successful\")</script>");
        out.flush();
        return "sellItems";
    }
    @RequestMapping(value="/sellItems.htm", method= RequestMethod.GET)
    protected String sellItems(HttpServletResponse response,ModelMap model) throws IOException {
        if(session.getAttribute("user")==null) {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.print("<script language='javascript'>alert(\"Please log in\")</script>");
            out.flush();
            model.addAttribute("allItem",itemDAO.getAllItems());
            return "item";
        }else {
            return "sellItems";
        }
    }
    @RequestMapping(value="/removeItem.htm", method= RequestMethod.GET)
    protected String removeItem(String itemID) {
        itemDAO.removeItem(itemID);
        CustomerEntity user=(CustomerEntity)session.getAttribute("user");
        CustomerEntity user1=customerDAO.getCustomer(user.getUsername());
        session.setAttribute("user",user1);
            return "sellItems";
        }
    @RequestMapping(value="/editItem.htm", method= RequestMethod.GET)
    protected String editItem(ModelMap model,int item) {
      ItemEntity i=itemDAO.getItemById(item);
      model.addAttribute("item",i);
      return "editItem";
    }
    @RequestMapping(value="/editItem.htm", method= RequestMethod.POST)
    protected String edit(@Valid ItemEntity item,BindingResult result,@RequestParam(value = "pic", required = false) MultipartFile multipartFile, HttpServletResponse response, HttpServletRequest request,ModelMap model) throws IOException {
        ItemEntity oldItem=itemDAO.getItemById(item.getItemId());
        String filename = null;
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        if(request.getAttribute("unsafe_request")=="true"){
            out.print("<script>alert(\"Unsafe Request\")</script>");
            out.flush();
            model.addAttribute("item",oldItem);
            return "editItem";
        }
        if(result.hasErrors()){
            List<ObjectError> errors=result.getAllErrors();
            for(ObjectError o:errors){
                out.print("<script>alert(\""+o.getDefaultMessage()+"\")</script>");
                out.flush();
                model.addAttribute("item",oldItem);
                return "editItem";
            }
        }
        if (!multipartFile.isEmpty()) {
            String path = request.getSession().getServletContext().getRealPath("WEB-INF/img" + File.separator + "ItemImg");
            String oldFileName = multipartFile.getOriginalFilename();
            String suffix = FilenameUtils.getExtension(oldFileName);
            long size = multipartFile.getSize();
            if (size > 454000) {
                out.print("<script>alert(\"Picture size cannot exceed 500kb\")</script>");
                out.flush();
                return null;
            } else if (suffix.equalsIgnoreCase("jpg") ||
                    suffix.equalsIgnoreCase("png") ||
                    suffix.equalsIgnoreCase("jpeg")){
                String NewFileName = System.currentTimeMillis() + RandomUtils.nextInt(100000) + "." + suffix;
                File targetFile = new File(path, NewFileName);
                if (!targetFile.exists()) {
                    targetFile.mkdirs();
                }
                try {
                    multipartFile.transferTo(targetFile);
                } catch (IOException e) {
                    e.printStackTrace();
                    out.print("<script>alert(\"Upload Picture Error\")</script>");
                    out.flush();
                    return null;
                }
                filename = NewFileName;
            }
            else{
                out.print("<script>alert(\"Picture format error\")</script>");
                out.flush();
                return null;
            }
            item.setPicture(filename);
        }else{
            item.setPicture(oldItem.getPicture());
        }
        item.setCustomer(oldItem.getCustomer());
        itemDAO.updateItem(item);
        CustomerEntity user=(CustomerEntity)session.getAttribute("user");
        CustomerEntity user1=customerDAO.getCustomer(user.getUsername());
        session.setAttribute("user",user1);
        out.print("<script>alert(\"Edit successful\")</script>");
        out.flush();
        return "sellItems";
    }
    @RequestMapping(value="/item.htm", method= RequestMethod.GET)
    protected String item(ModelMap model) {
        model.addAttribute("allItem",itemDAO.getAllItems());
        return "item";
    }

    @RequestMapping(value="/viewDetail.htm", method= RequestMethod.GET)
    protected String viewDetail(ModelMap model,int item,String username) {
        String type;
        ItemEntity i=itemDAO.getItemById(item);
        if(session.getAttribute("user")==null) {
            type = "buy";
        }
        else{
            CustomerEntity user=(CustomerEntity)session.getAttribute("user");
            if(user.getUsername().equals(username)||user.getCart().contains(i)){
                type="see";
            }
            else type="buy";
        }
        model.addAttribute("item",i);
        model.addAttribute("type",type);
        return "itemDetail";
    }


}

