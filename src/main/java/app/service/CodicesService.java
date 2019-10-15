package app.service;

import app.Dao.CodDao;
import app.model.Codicesc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Service("codicesService")
@Transactional
public class CodicesService {

    @Autowired
    private CodDao codDao;

    public void listCod(ModelMap model)
            throws SQLException, IOException, ServletException {
        List<Codicesc> listCod = codDao.getAllCod();
     model.addAttribute("listCod",listCod);
    }

    public void insertCod(Codicesc newcod)
            throws SQLException, IOException {
        codDao.saveCod(newcod);
    }

    public void updateCod(Codicesc cod,int id)
            throws SQLException, IOException {
        cod.setId(id);
        codDao.updateCod(cod);
    }

    public void deleteCod(int id)
            throws SQLException, IOException {
        codDao.deleteCod(id);
    }
}
