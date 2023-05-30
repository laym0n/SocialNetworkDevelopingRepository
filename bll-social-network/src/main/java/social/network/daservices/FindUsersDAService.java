package social.network.daservices;

import social.network.entities.socialnetworkuser.HumanUser;

import java.util.List;

public interface FindUsersDAService {
    List<HumanUser> findUsersWithFirstNameAndSecondNameContainingSearchString(String searchString);
}
