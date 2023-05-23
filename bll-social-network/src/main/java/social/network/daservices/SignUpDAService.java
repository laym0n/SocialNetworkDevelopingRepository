package social.network.daservices;

import social.network.entities.user.User;
import social.network.entities.user.UserInfo;

public interface SignUpDAService {
    void createUserWithUserInfo(User user, UserInfo userInfo);
}
