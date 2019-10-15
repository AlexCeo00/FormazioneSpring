package app.service;

import app.Dao.PrenDao;
import app.Dao.ProblDao;
import app.Dao.UserDao;
import app.model.Probl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Service("problService")
@Transactional
public class ProblService{
    @Autowired
    private ProblDao problDao;
    @Autowired
    private PrenDao prenDao;
    @Autowired
    private UserDao userDao;

    public void listProbl1(int idut, ModelMap model)
            throws SQLException, IOException, ServletException {
      //  int id = Integer.parseInt(request.getParameter("id"));
        List <Probl> listProbl = problDao.getProblid(idut);
     //   request.setAttribute("listProbl", listProbl);
        model.addAttribute("listProbl", listProbl);
        model.addAttribute("idut", idut);
    }

        public void insertProbl(Probl p)
            throws SQLException, IOException {
        problDao.saveProbl(p);
    }

    public void updateProbl(Probl p, int idprobl)
            throws SQLException, IOException {
                p.setId(idprobl);
            problDao.updateProbl(p);
    }

    public void deleteProbl(int id)
            throws SQLException, IOException {
        problDao.deleteProbl(id);
    }
}
