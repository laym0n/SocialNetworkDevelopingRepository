package social.network.implbllservices.usersservices;

import social.network.dao.UserRoleDAO;
import social.network.daservices.SignInDAService;
import social.network.entities.UserRoleEntity;
import social.network.entities.user.User;
import social.network.entities.user.UserInfo;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import social.network.dao.UserDAO;
import social.network.entities.UserEntity;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class SignInDAServiceImpl implements SignInDAService {
    private UserDAO userDAO;
    private UserRoleDAO userRoleDAO;
    @Override
    public void createSimpleUserWithUserInfo(User user, UserInfo userInfo) {
        UserRoleEntity simpleUserRole = userRoleDAO.findByName("SIMPLE_USER");
        ArrayList<UserRoleEntity> roles = new ArrayList<>();
        roles.add(simpleUserRole);

        UserEntity newUser = new UserEntity(user, userInfo);
        newUser.setRoles(roles);
        try {
            userDAO.save(newUser);
        } catch (DataIntegrityViolationException exception) {
            throw new DuplicateKeyException("User with username " + user.getUserName() + " already exists");
        }
    }

    @Override
    public String getUserPasswordByUserName(String userName) {
        return userDAO.findPasswordByUserName(userName);
    }
}
