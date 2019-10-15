package app.controller;

import app.Dao.RuoliDao;
import app.model.Ruoli;
import app.service.RuoliService;
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

@Controller
public class RuoliController {

    @Autowired
    private RuoliDao ruoDao;
    @Autowired
    RuoliService service;
    @Autowired
    RuoliService rservice;


    //RUOLI

    //creazione dei Ruoli
    @RequestMapping(value = "/newRuo", method = RequestMethod.GET)
    public ModelAndView newRuolo(ModelAndView model) {
        Ruoli ruo = new Ruoli();
        model.addObject("ruo", ruo);
        model.setViewName("formruolo");
        return model;
    }

    @RequestMapping(value = "/saveRuo", method = RequestMethod.POST)
    public ModelAndView saveRuo(@ModelAttribute Ruoli ruo, @RequestParam(name = "id", required = false) int id) throws IOException, SQLException {
        if (ruo.getId() == 0) {
            rservice.insertRuo(ruo);
        } else {
            rservice.updateRuo(ruo, id);
        }
        return new ModelAndView("redirect:/Ruoli");
    }

    //eliminazione del ruolo
    @RequestMapping(value = "/deleteruo", method = RequestMethod.GET)
    public ModelAndView deleteRuo(@RequestParam(name = "id") int id) throws IOException, SQLException {
        rservice.deleteRuo(id);
        return new ModelAndView("redirect:/Ruoli");
    }


    @RequestMapping(value = "/editruo", method = RequestMethod.GET)
    public ModelAndView editR(@RequestParam(name = "id") int id) {
        Ruoli ruo = ruoDao.getRuo(id);
        ModelAndView model = new ModelAndView("formruolo");
        model.addObject("ruo", ruo);
        return model;
    }


    @RequestMapping(value = "/Ruoli", method = RequestMethod.GET)
    public String login(ModelMap model) throws ServletException, SQLException, IOException {
        service.listRuo(model);
        return "ruoli";}
    }

