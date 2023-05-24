package social.network.jpa.implbllservices.usersservices;

import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import social.network.entities.user.PersonalInfo;
import social.network.jpa.dao.UserDAO;
import social.network.jpa.dao.UserRoleDAO;
import social.network.daservices.SignUpDAService;
import social.network.jpa.entities.UserEntity;
import social.network.jpa.entities.UserRoleEntity;
import social.network.entities.user.User;
import social.network.entities.user.UserRole;
import social.network.exceptions.EntityAlreadyExistsException;

@Service
@AllArgsConstructor
public class JPASignUpDAService implements SignUpDAService {
    private UserDAO userDAO;
    private UserRoleDAO userRoleDAO;
    @Override
    public void createUserWithPersonalInfo(User user, PersonalInfo personalInfo) {
        UserEntity newUserEntity = new UserEntity(user, personalInfo);
        for (UserRole userRole : user.getRoles().values()) {
            UserRoleEntity userRoleEntity = userRoleDAO.findByName(userRole.name());
            newUserEntity.getRoles().add(userRoleEntity);
        }
        try {
            newUserEntity = userDAO.create(newUserEntity);
        } catch (DataIntegrityViolationException exception) {
            throw new EntityAlreadyExistsException("User with username " + user.getUserName() + " already exists");
        }
        user.setId(newUserEntity.getId());
    }
}
