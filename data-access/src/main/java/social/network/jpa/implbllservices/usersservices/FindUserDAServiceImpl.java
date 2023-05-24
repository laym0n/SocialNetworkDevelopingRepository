package social.network.jpa.implbllservices.usersservices;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import social.network.jpa.jpadao.JPAUserDAO;
import social.network.daservices.FindUsersDAService;
import social.network.jpa.entities.UserEntity;
import social.network.entities.user.UserInfo;

import java.util.List;

@Service
@AllArgsConstructor
public class FindUserDAServiceImpl implements FindUsersDAService {
    private JPAUserDAO JPAUserDAO;
    @Override
    public List<UserInfo> findUsersWithFirstNameAndSecondNameContainingSearchString(String searchString) {
        List<UserEntity> users =  JPAUserDAO.findAllUsersWithFirstNameAndSecondNameContainingString(searchString);
        return users.stream()
                .map(UserEntity::getUserInfo).toList();
    }
}
