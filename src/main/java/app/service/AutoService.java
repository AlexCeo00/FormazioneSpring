package app.service;

import app.Dao.AutoDao;
import app.model.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Service("autoService")
@Transactional
public class AutoService {

    @Autowired
    private AutoDao autoDao;

    public void listAuto(ModelMap model)
            throws SQLException, IOException, ServletException {
        List<Auto> listAuto = autoDao.getAllAuto();
        model.addAttribute("listAuto", listAuto);
    }

    public void insertAuto(Auto newAuto)
            throws SQLException, IOException {
        autoDao.saveAuto(newAuto);;
    }

    public void updateAuto(Auto auto,int id)
            throws SQLException, IOException {
        auto.setId(id);
        autoDao.updateAuto(auto);
    }

    public void deleteAuto(int id)
            throws SQLException, IOException {
        autoDao.deleteAuto(id);
    }

}
