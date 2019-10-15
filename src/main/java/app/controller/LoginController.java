package app.controller;

import app.Dao.LoginDao;
import app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Autowired
    private LoginDao loginDao;

    @RequestMapping(value = "/Login", method = RequestMethod.POST)
    public ModelAndView login(@RequestParam(name = "nome") String username, @RequestParam(name = "passut") String password, HttpServletRequest reques, ModelMap model) {
//        String username = reques.getParameter("nome");
//        String password = reques.getParameter("passut");
        User user = new User();
        User user1 = new User();
        user.setNome(username);
        user.setPassut(password);
        try {if(loginDao.isPresent(user)!=null) {
            user1 = loginDao.isPresent(user);
            HttpSession session = reques.getSession();
            String s = user1.getTipologia();
            session.setAttribute("user", user1);
            if (s.equals("Admin"))
                return new ModelAndView("redirect:/User");
            else if (
                    s.equals("Customer"))
                return new ModelAndView("redirect:/Prenotazione?id="+user1.getId());
        }else{
            model.addAttribute("er1",true);
            return new ModelAndView("redirect:index.jsp");
        }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
   return null; }

}