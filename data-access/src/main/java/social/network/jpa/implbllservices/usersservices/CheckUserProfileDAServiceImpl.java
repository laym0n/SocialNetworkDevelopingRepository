package social.network.jpa.implbllservices.usersservices;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import social.network.jpa.dao.*;
import social.network.jpa.entities.ids.BlackListUserEntityId;
import social.network.jpa.entities.ids.FriendRequestEntityId;
import social.network.jpa.jpadao.JPAUserDAO;
import social.network.daservices.CheckUserProfileDAService;
import social.network.jpa.entities.UserEntity;
import social.network.entities.user.UserInfo;
import social.network.entities.user.UserProfile;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CheckUserProfileDAServiceImpl implements CheckUserProfileDAService {
    private UserDAO JPAUserDAO;
    private FriendDAO friendDAO;
    private FriendRequestDAO friendRequestDAO;
    private UsersBlackListDAO usersBlackListDAO;
    private ChatDAO chatDAO;

    @Override
    public UserProfile loadUserProfileById(int idUserTarget, int idOwnerRequest) throws AccountNotFoundException {
        Optional<UserEntity> optionalUser = JPAUserDAO.findById(idUserTarget);
        if (optionalUser.isEmpty()) {
            throw new AccountNotFoundException("User with id " + idUserTarget + " not found");
        }
        UserEntity user = optionalUser.get();
        UserProfile result = user.getUserProfile();
        return result;
    }

    @Override
    public List<UserInfo> loadFriendWithoutAvatars(int countFriends) {
        return null;
    }

    @Override
    public boolean isFriendRelationshipExist(int idFirstUser, int idSecondUser) {
        return friendDAO.existsWithUserIds(idFirstUser, idSecondUser);
    }

    @Override
    public boolean isFriendRequestExist(int idFirstUser, int idSecondUser) {
        return friendRequestDAO.isExistById(new FriendRequestEntityId(idFirstUser, idSecondUser));
    }

    @Override
    public boolean isUserInBlackListOfOtherUser(int idFirstUser, int idSecondUser) {
        return usersBlackListDAO.existsById(new BlackListUserEntityId(idSecondUser, idFirstUser));
    }

    @Override
    public boolean isDialogChatExistBetweenUsers(int idUserTarget, int idOwnerRequest) {
        return false;
    }

    @Override
    public Optional<Integer> findIdDialogChat(int idFirstUser, int idSecondUser) {
        return chatDAO.findIdDialogChatBetweenUsers(idFirstUser, idSecondUser);
    }
}
