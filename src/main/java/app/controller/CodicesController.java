package app.controller;

import app.Dao.CodDao;
import app.model.Codicesc;
import app.service.CodicesService;
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
public class CodicesController {
    @Autowired
    private CodDao codDao;
    @Autowired
    CodicesService service;


    public void init() {
        codDao = new CodDao();
    }

//CODICE SCONTO

    @RequestMapping(value = "/Codice", method = RequestMethod.GET)
    public String probl(ModelMap model)
            throws ServletException, SQLException, IOException {
        service.listCod(model);
        return "codices";
    }


    //creazione del codice cesconto
    @RequestMapping(value = "/newCod", method = RequestMethod.GET)
    public ModelAndView newCodicesc(ModelAndView model) {
        Codicesc cod = new Codicesc();
        model.addObject("cod", cod);
        model.setViewName("formcodicesc");
        return model;
    }

    @RequestMapping(value = "/saveCod", method = RequestMethod.POST)
    public ModelAndView saveCod(@ModelAttribute Codicesc cod, @RequestParam(name = "id", required = false) int id) throws IOException, SQLException {
        if (cod.getId() == 0) { // if employee id is 0 then creating the
            // employee other updating the employee

            service.insertCod(cod);
        } else {
            service.updateCod(cod, id);
        }
        return new ModelAndView("redirect:/Codice");
    }

    //eliminazione del codice sconto
    @RequestMapping(value = "/deletecod", method = RequestMethod.GET)
    public ModelAndView deleteCod(@RequestParam(name = "id") int id) throws IOException, SQLException {
        service.deleteCod(id);
        return new ModelAndView("redirect:/Codice");
    }


    @RequestMapping(value = "/editc", method = RequestMethod.GET)
    public ModelAndView editCod(@RequestParam(name = "id") int id) {
        Codicesc cod = codDao.getCod(id);
        ModelAndView model = new ModelAndView("formcodicesc");
        model.addObject("cod", cod);
        return model;
    }

}

