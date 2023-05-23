package social.network.implbllservices.usersservices;

import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import social.network.dao.UserDAO;
import social.network.dao.UserRoleDAO;
import social.network.daservices.SignUpDAService;
import social.network.entities.UserEntity;
import social.network.entities.UserRoleEntity;
import social.network.entities.user.User;
import social.network.entities.user.UserInfo;
import social.network.entities.user.UserRole;
import social.network.exceptions.EntityAlreadyExistsException;

@Service
@AllArgsConstructor
public class SignUpDAServiceImpl implements SignUpDAService {
    private UserDAO userDAO;
    private UserRoleDAO userRoleDAO;
    @Override
    public void createUserWithUserInfo(User user, UserInfo userInfo) {
        UserEntity newUserEntity = new UserEntity(user, userInfo);
        for (UserRole userRole : user.getRoles().values()) {
            UserRoleEntity userRoleEntity = userRoleDAO.findByName(userRole.name());
            newUserEntity.getRoles().add(userRoleEntity);
        }
        try {
            newUserEntity = userDAO.save(newUserEntity);
        } catch (DataIntegrityViolationException exception) {
            throw new EntityAlreadyExistsException("User with username " + user.getUserName() + " already exists");
        }
        user.setId(newUserEntity.getId());
        userInfo.setIdUser(newUserEntity.getId());
    }
}
