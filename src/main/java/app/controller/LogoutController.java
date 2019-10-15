package app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LogoutController {
    @RequestMapping(value = "/Logout", method = RequestMethod.POST)
    public String login(HttpServletRequest reques, ModelMap model) {
            HttpSession session = reques.getSession(false);;
            session.removeAttribute("user");
            session.getMaxInactiveInterval();
            return "../index";
        }
}
