package controllers;

import DAO.ItemDAO;
import POJOs.ItemEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MenuController {
    @Autowired
    ItemDAO itemDAO;
    @RequestMapping(value="/menu.htm", method= RequestMethod.GET)
    protected String menu() {
        return "menu";
    }
    @RequestMapping(value="/searchByKeyWord.json", method= RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    protected List<String> searchByKeyWord(String keyWord) {
        List<String> list=new ArrayList<String>();
        List<ItemEntity> result= (List<ItemEntity>)itemDAO.searchByKeyWord(keyWord);
        for(ItemEntity i:result){
            list.add(i.getItemName());
        }
        return list;
    }
    @RequestMapping(value="/search.htm", method= RequestMethod.GET)
    protected String search(String keyWord, ModelMap model) {
        model.addAttribute("allItem",itemDAO.searchByKeyWord(keyWord));
        return "item";
    }
    @RequestMapping(value="/books.htm", method= RequestMethod.GET)
    protected String books(ModelMap model) {
        model.addAttribute("allItem",itemDAO.searchByType("books"));
        return "item";
    }
    @RequestMapping(value="/games.htm", method= RequestMethod.GET)
    protected String games(ModelMap model) {
        model.addAttribute("allItem",itemDAO.searchByType("games"));
        return "item";
    }
    @RequestMapping(value="/computers.htm", method= RequestMethod.GET)
    protected String computers(ModelMap model) {
        model.addAttribute("allItem",itemDAO.searchByType("computers"));
        return "item";
    }
    @RequestMapping(value="/sports.htm", method= RequestMethod.GET)
    protected String sports(ModelMap model) {
        model.addAttribute("allItem",itemDAO.searchByType("sports"));
        return "item";
    }
    @RequestMapping(value="/furniture.htm", method= RequestMethod.GET)
    protected String furniture(ModelMap model) {
        model.addAttribute("allItem",itemDAO.searchByType("furniture"));
        return "item";
    }
    @RequestMapping(value="/other.htm", method= RequestMethod.GET)
    protected String other(ModelMap model) {
        model.addAttribute("allItem",itemDAO.searchByType("other"));
        return "item";
    }
}
