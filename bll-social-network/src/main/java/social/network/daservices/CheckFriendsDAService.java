package social.network.daservices;

import social.network.entities.socialnetworkuser.HumanUser;

import java.util.List;

public interface CheckFriendsDAService {
    List<HumanUser> findFriendsInfoForUser(int idUser);
}
