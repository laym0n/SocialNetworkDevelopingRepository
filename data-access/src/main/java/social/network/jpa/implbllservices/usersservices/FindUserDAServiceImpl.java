package social.network.jpa.implbllservices.usersservices;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import social.network.entities.socialnetworkuser.HumanUser;
import social.network.jpa.jpadao.JPAUserDAO;
import social.network.daservices.FindUsersDAService;
import social.network.jpa.entities.UserEntity;

import java.util.List;

@Service
@AllArgsConstructor
public class FindUserDAServiceImpl implements FindUsersDAService {
    private JPAUserDAO JPAUserDAO;
    @Override
    public List<HumanUser> findUsersWithFirstNameAndSecondNameContainingSearchString(String searchString) {
        List<UserEntity> users =  JPAUserDAO.findAllUsersWithFirstNameAndSecondNameContainingString(searchString);
        return users.stream()
                .map(UserEntity::getHumanUser).toList();
    }
}
