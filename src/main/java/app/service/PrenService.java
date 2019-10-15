package app.service;

import app.Dao.*;
import app.model.Pren;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Service("prenService")
@Transactional
public class PrenService {
    @Autowired
    private ProblDao problDao;
    @Autowired
    private PrenDao prenDao;
    @Autowired
    private AutoDao autoDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private CodDao codDao;

    public void listPren1(int idut,ModelMap model)
            throws SQLException, IOException, ServletException {
        List <Pren> listPren = this.prenDao.getPrenid(idut);
       // request.setAttribute("listPren", listPren);
        model.addAttribute("listPren", listPren);
        model.addAttribute("idut", idut);
    }

    public void insertPren(Pren p)
            throws SQLException, IOException {
        prenDao.savePren(p);
    }

    public void updatePren(Pren p, int idpren) //uso questo
            throws SQLException, IOException {
        p.setId(idpren);
        prenDao.updatePren(p);
    }

    public void deletePren(int id)
            throws SQLException, IOException {
        prenDao.deletePren(id);
    }

    public void approvPren(int id)
            throws SQLException, IOException {
        prenDao.ApprPren(id);
    }

    public void napprovPren(int id)
            throws SQLException, IOException {
        prenDao.NApprPren(id);

    }
}
