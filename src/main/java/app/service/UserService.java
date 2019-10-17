package app.service;

import app.Dao.RuoliDao;
import app.Dao.UserDao;
import app.model.Ruoli;
import app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Service("userService")
@Transactional
public class UserService {
    @Autowired//injection
    private UserDao userDao;
    @Autowired
    private RuoliDao ruoDao;

    public void listUser(ModelMap model)
            throws SQLException, IOException, ServletException {
        List<User> listUser = userDao.getAllUser();
        model.addAttribute("listUser", listUser);
    }

    public void insertUser(User user) throws SQLException, IOException {
        int idtipo=Integer.parseInt(user.getTipologia());
        Ruoli r=new Ruoli();
        r=ruoDao.getRuo(idtipo);
        user.setTipologia(r.getDettagli());
        userDao.saveUser(user);
    }

    public void updateUser(User user,int id)
            throws SQLException, IOException {
        int idtipo=Integer.parseInt(user.getTipologia());
        Ruoli r=new Ruoli();
        r=ruoDao.getRuo(idtipo);
        user.setTipologia(r.getDettagli());
        user.setId(id);
        userDao.updateUser(user);
    }

    public void deleteUser(int id) throws SQLException, IOException {
        userDao.deleteUser(id);
    }

}
