package social.network.daservices;

import social.network.entities.user.UserInfo;

import java.util.List;

public interface FindUsersDAService {
    List<UserInfo> findUsersWithFirstNameAndSecondNameContainingSearchString(String searchString);
}
