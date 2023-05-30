package social.network.daservices;

import social.network.entities.user.UserInfo;
import social.network.entities.user.UserProfile;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;
import java.util.Optional;

public interface CheckUserProfileDAService {
    UserProfile loadUserProfileById(int idUserTarget, int idOwnerRequest) throws AccountNotFoundException;

    List<UserInfo> loadFriendWithoutAvatars(int countFriends);

    boolean isFriendRelationshipExist(int idUserTarget, int idOwnerRequest);

    boolean isFriendRequestExist(int idUserTarget, int idOwnerRequest);

    boolean isUserInBlackListOfOtherUser(int idOwnerRequest, int idUserTarget);

    boolean isDialogChatExistBetweenUsers(int idUserTarget, int idOwnerRequest);

    Optional<Integer> findIdDialogChat(int idOwnerRequest, int idUserTarget);
}
