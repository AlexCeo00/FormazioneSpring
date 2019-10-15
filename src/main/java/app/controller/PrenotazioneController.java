package app.controller;

import app.Dao.AutoDao;
import app.Dao.CodDao;
import app.Dao.PrenDao;
import app.Dao.UserDao;
import app.model.Auto;
import app.model.Codicesc;
import app.model.Pren;
import app.model.User;
import app.service.PrenService;
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
public class PrenotazioneController {
    @Autowired
    private PrenDao prenDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private AutoDao autoDao;
    @Autowired
    private CodDao codDao;
    @Autowired
    PrenService service;


    @RequestMapping(value = "/Prenotazione", method = RequestMethod.GET)
    public String pren(ModelMap model,
                        @RequestParam(name = "id") int id)
            throws ServletException, SQLException, IOException {
        service.listPren1(id, model);
        return "prenotazioni";
    }

    //Prenotazioni
    @RequestMapping(value = "/newPren", method = RequestMethod.GET)
    public ModelAndView newPren(
            ModelAndView model,
            @RequestParam(name = "id2") int id
    ) {
        Pren p = new Pren();
        List<Auto> listAuto = autoDao.getAllAuto();
        model.addObject("listAuto", listAuto);
        model.addObject("pren", p);
        model.addObject("idut", id);
        model.setViewName("formpren");
        return model;
    }

    @RequestMapping(value = "/savePren", method = RequestMethod.POST)
    public ModelAndView savePren(@ModelAttribute Pren p,
                                 @RequestParam(name = "idcodicesc", required = false) int idcod,
                                 @RequestParam(name = "id", required = false) int id,
                                 @RequestParam(name = "id2", required = false) int idut)
//                                 @RequestParam(name = "idauto", required = false) int ida)
            throws IOException, SQLException {
        if (p.getId() == 0) { // if employee id is 0 then creating the
            // employee other updating the employee
            //     p.setPren(prenDao.getPren(idp));
//            Auto a=new Auto();
//            a=autoDao.getAuto(ida);
            Codicesc cod=new Codicesc();
            p.setStato("No");
            cod=codDao.getCod(idcod);
//            p.setAuto(a);
            User u=new User();
            u=userDao.getUser(idut);
            p.setUser(u);
            p.setCodicesc(cod);
            service.insertPren(p);
        } else {
//            p.setAuto(autoDao.getAuto(ida));
            p.setUser(userDao.getUser(idut));
            p.setStato("No");
            Codicesc cod=new Codicesc();
            cod=codDao.getCod(idcod);
            p.setCodicesc(cod);
            service.updatePren(p,id);
        }
        return new ModelAndView("redirect:/Prenotazione?id="+idut);
    }

    //eliminazione della prenotazione
    @RequestMapping(value = "/deletePren", method = RequestMethod.GET)
    public ModelAndView deletePren(@RequestParam(name = "id") int id) throws IOException, SQLException {
        service.deletePren(id);
        return new ModelAndView("redirect:/Prenotazione");
    }

    //eliminazione della prenotazione
    @RequestMapping(value = "/apprPren", method = RequestMethod.GET)
    public ModelAndView approvaPren(
            @RequestParam(name = "id") int id,
            @RequestParam(name = "id2", required = false) int idut
    ) throws IOException, SQLException {
        service.approvPren(id);
        return new ModelAndView("redirect:/Prenotazione?id="+idut);
    }

    //eliminazione della prenotazione
    @RequestMapping(value = "/napprPren", method = RequestMethod.GET)
    public ModelAndView napprovaPren(
            @RequestParam(name = "id") int id,
            @RequestParam(name = "id2", required = false) int idut
    ) throws IOException, SQLException {
        service.napprovPren(id);
        return new ModelAndView("redirect:/Prenotazione?id="+idut);
    }
    @RequestMapping(value = "/editPren", method = RequestMethod.GET)
    public ModelAndView editPren(
            @RequestParam(name = "id") int id,
            @RequestParam(name = "id2") int idut
    ) {
        Pren p = prenDao.getPren(id);
        List<Auto> listAuto = autoDao.getAllAuto();
        ModelAndView model = new ModelAndView("formpren");
        model.addObject("listAuto", listAuto);
        model.addObject("idut", idut);
        model.addObject("pren", p);
        return model;
    }

}