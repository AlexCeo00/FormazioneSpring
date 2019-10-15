package app.controller;

import app.Dao.AutoDao;
import app.model.Auto;
import app.service.AutoService;
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
public class AdminParkController {

        @Autowired
        private AutoDao autoDao;
        @Autowired
        AutoService service;
        @Autowired
        AutoService aservice;

    //Auto

    @RequestMapping(value = "/newAuto", method = RequestMethod.GET)
    public ModelAndView newAuto(ModelAndView model) {
        Auto auto = new Auto();
        model.addObject("auto", auto);
        model.setViewName("formauto");
        return model;
    }

    @RequestMapping(value = "/saveAuto", method = RequestMethod.POST)
    public ModelAndView saveAuto(@ModelAttribute Auto auto, @RequestParam(name = "id", required = false) int id) throws IOException, SQLException {
        if (auto.getId() == 0) { // if employee id is 0 then creating the
            // employee other updating the employee
            aservice.insertAuto(auto);
        } else {
            aservice.updateAuto(auto, id);
        }
        return new ModelAndView("redirect:/Park");
    }

    //eliminazione del ruolo
    @RequestMapping(value = "/deleteauto", method = RequestMethod.GET)
    public ModelAndView deleteAuto(@RequestParam(name = "id") int id) throws IOException, SQLException {
        aservice.deleteAuto(id);
        return new ModelAndView("redirect:/Park");
    }


    @RequestMapping(value = "/editauto", method = RequestMethod.GET)
    public ModelAndView editA(@RequestParam(name = "id") int id) {
        Auto auto = autoDao.getAuto(id);
        ModelAndView model = new ModelAndView("formauto");
        model.addObject("auto", auto);
        return model;
    }

    @RequestMapping(value = "/Park", method = RequestMethod.GET)
    public String Vistaparco(ModelMap model) throws ServletException, SQLException, IOException {
        service.listAuto(model);
        return "ParcoAutoAdmin";
        }


}
