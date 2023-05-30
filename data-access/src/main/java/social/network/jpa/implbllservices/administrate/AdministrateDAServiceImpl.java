package social.network.jpa.implbllservices.administrate;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import social.network.daservices.AdministrateDAService;
import social.network.jpa.dao.UserDAO;

@Service
@AllArgsConstructor
public class AdministrateDAServiceImpl implements AdministrateDAService {
    private UserDAO userDAO;
    @Override
    @Transactional
    public void setIsBlockedForUser(int idUser, boolean value) {
        userDAO.setIsBlocked(idUser, value);
    }
}
