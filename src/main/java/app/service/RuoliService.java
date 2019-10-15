package app.service;

import app.Dao.RuoliDao;
import app.model.Ruoli;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import javax.servlet.ServletException;
//import javax.transaction.Transactional;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Service("ruoService")
@Transactional
public class RuoliService {
    @Autowired
    private RuoliDao ruoDao;

    public void listRuo(ModelMap model)
            throws SQLException, IOException, ServletException {
        List<Ruoli> listRuo = ruoDao.getAllRuo();
        model.addAttribute("listRuo", listRuo);
    }


    public void insertRuo(Ruoli newRuo)
            throws SQLException, IOException {
        newRuo.setDele("no");
        ruoDao.saveRuo(newRuo);
    }

    public void updateRuo(Ruoli ruo,int id)
            throws SQLException, IOException {
        ruo.setId(id);
        ruoDao.updateRuo(ruo);
    }

    public void deleteRuo(int id)
            throws SQLException, IOException {
        ruoDao.deleteRuo(id);
    }
}
