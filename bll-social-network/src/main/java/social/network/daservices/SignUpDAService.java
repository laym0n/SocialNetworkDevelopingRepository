package social.network.daservices;

import social.network.entities.user.PersonalInfo;
import social.network.entities.user.User;

public interface SignUpDAService {
    void createUserWithPersonalInfo(User user, PersonalInfo personalInfo);
}
