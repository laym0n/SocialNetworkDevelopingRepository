package social.network.daservices;

import social.network.entities.user.User;
import social.network.entities.user.UserInfo;

public interface SignInDAService {
    void createSimpleUserWithUserInfo(User user, UserInfo userInfo);

    String getUserPasswordByUserName(String userName);
}
