package social.network.daservices;

import social.network.entities.socialnetworkuser.HumanUser;

import java.util.List;

public interface CheckFriendRequestsDAService {
    List<HumanUser> findFriendRequestToUser(int idUserSenderRequest);
}
