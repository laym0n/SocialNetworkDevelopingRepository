package social.network.daservices;

import social.network.entities.user.PersonalInfo;
import social.network.entities.user.User;
import social.network.entities.user.UserProfile;

public interface SignUpDAService {
    void createUserWithPersonalInfo(User user, UserProfile userProfile);
}
