package app.controller;

import app.Dao.*;
import app.model.Pren;
import app.model.Probl;
import app.service.*;
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
public class ProblController {
    @Autowired
    private UserDao userDao;
    @Autowired
    private AutoDao autoDao;
    @Autowired
    private PrenDao prenDao;
    @Autowired
    private ProblDao problDao;
    @Autowired
    ProblService service;
    @Autowired
    private CodDao codDao;
    @Autowired
    private RuoliDao ruoDao;
    @Autowired
    UserService uservice;
    @Autowired
    CodicesService cservice;
    @Autowired
    RuoliService rservice;
    @Autowired
    AutoService aservice;
    @Autowired
    ProblService pservice;

    @RequestMapping(value = "/Problema", method = RequestMethod.GET)
    public String probl(ModelMap model,
                        @RequestParam(name = "id") int id)
            throws ServletException, SQLException, IOException {
        service.listProbl1(id, model);
        return "problemi";
    }


    //Problemi

    @RequestMapping(value = "/newProbl", method = RequestMethod.GET)
    public ModelAndView newProbl(
            ModelAndView model,
            @RequestParam(name = "id2") int id
    ) {
        Probl p = new Probl();
        List<Pren> listPren = prenDao.getPrenid(id);
        model.addObject("listPren", listPren);
        model.addObject("probl", p);
        model.addObject("idut", id);
        model.setViewName("formprobl");
        return model;
    }

    @RequestMapping(value = "/saveProbl", method = RequestMethod.POST)
    public ModelAndView saveProbl(@ModelAttribute Probl p,
                                  @RequestParam(name = "id", required = false) int id,
                                  @RequestParam(name = "id2", required = false) int idut)
                            //      @RequestParam(name = "idprenotazione", required = false) int idp)
            throws IOException, SQLException {
        if (p.getId() == 0) {
           Pren pren=new Pren();
           pren=prenDao.getPren(p.getPren().getId());
           p.setPren(pren);
           pservice.insertProbl(p);
        } else {
                 p.setPren(prenDao.getPren(p.getPren().getId()));
                 pservice.updateProbl(p,id);
        }
        return new ModelAndView("redirect:/Problema?id="+idut);
    }

    //eliminazione della multa-problema
    @RequestMapping(value = "/deleteProbl", method = RequestMethod.GET)
    public ModelAndView deleteProbl(
            @RequestParam(name = "id") int id,
            @RequestParam(name = "id2", required = false) int idut
    ) throws IOException, SQLException {
        pservice.deleteProbl(id);
        return new ModelAndView("redirect:/Problema?id="+idut);
    }


    @RequestMapping(value = "/editProbl", method = RequestMethod.GET)
    public ModelAndView editP(
            @RequestParam(name = "id") int id,
            @RequestParam(name = "id2") int idut
    ) {
        Probl p = problDao.getProbl(id);
        List<Pren> listPren = prenDao.getPrenid(idut);
        ModelAndView model = new ModelAndView("formprobl");
        model.addObject("listPren", listPren);
        model.addObject("idut", idut);
        model.addObject("probl", p);
        return model;
    }

}
