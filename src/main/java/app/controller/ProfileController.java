package app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.SQLException;

@Controller
public class ProfileController {
        @RequestMapping(value = "/Profile", method = RequestMethod.GET)
        public String login(ModelMap model) throws ServletException, SQLException, IOException {
            return "profilo";
        }
    }

