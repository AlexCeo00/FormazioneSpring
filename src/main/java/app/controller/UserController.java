package app.controller;

import app.Dao.RuoliDao;
import app.Dao.UserDao;
import app.model.Ruoli;
import app.model.User;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserDao userDao;
    @Autowired
    UserService service;
    @Autowired
    private RuoliDao ruoDao;
    @Autowired
    UserService uservice;

    @RequestMapping(value = "/User", method = RequestMethod.GET)
    public String login(ModelMap model) throws ServletException, SQLException, IOException {
                        service.listUser(model);
        return "admin";}


    //creazione dello user
    @RequestMapping(value = "/newUser", method = RequestMethod.GET)
    public ModelAndView newContact(ModelAndView model) {
        User user = new User();
        List<Ruoli> listRuoli = ruoDao.getAllRuo();
        model.addObject("listRuo", listRuoli);
        model.addObject("user", user);
        model.setViewName("formutente");
        return model;
    }

    @RequestMapping(value = "/saveEmployee", method = RequestMethod.POST)
    public ModelAndView saveEmployee(@ModelAttribute User user, @RequestParam(name = "id", required = false) int id) throws IOException, SQLException {
        if (user.getId() == 0) {
            uservice.insertUser(user);
        } else {
            uservice.updateUser(user, id);
        }
        return new ModelAndView("redirect:/User");
    }

    //eliminazione dello user
    @RequestMapping(value = "/deleteusr", method = RequestMethod.GET)
    public ModelAndView deleteUser(@RequestParam(name = "id") int id) throws IOException, SQLException {
        uservice.deleteUser(id);
        return new ModelAndView("redirect:/User");
    }


    @RequestMapping(value = "/editu", method = RequestMethod.GET)
    public ModelAndView editUser(@RequestParam(name = "id") int id) {
        User user = userDao.getUser(id);
        ModelAndView model = new ModelAndView("formutente");
        List<Ruoli> listRuo = ruoDao.getAllRuo();
        model.addObject("listRuo", listRuo);
        model.addObject("user", user);
        return model;
    }


}