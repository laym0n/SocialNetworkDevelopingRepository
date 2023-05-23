package social.network.implbllservices.usersservices;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import social.network.dao.UserDAO;
import social.network.daservices.FindUsersDAService;
import social.network.entities.UserEntity;
import social.network.entities.user.UserInfo;

import java.util.List;

@Service
@AllArgsConstructor
public class FindUserDAServiceImpl implements FindUsersDAService {
    private UserDAO userDAO;
    @Override
    public List<UserInfo> findUsersWithFirstNameAndSecondNameContainingSearchString(String searchString) {
        List<UserEntity> users =  userDAO.findAllUsersWithFirstNameAndSecondNameContainingString(searchString);
        return users.stream()
                .map(UserEntity::getUserInfo).toList();
    }
}
