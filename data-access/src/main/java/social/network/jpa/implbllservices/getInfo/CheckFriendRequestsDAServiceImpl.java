package social.network.jpa.implbllservices.getInfo;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import social.network.daservices.CheckFriendRequestsDAService;
import social.network.daservices.CheckFriendsDAService;
import social.network.entities.socialnetworkuser.HumanUser;
import social.network.jpa.dao.FriendDAO;
import social.network.jpa.dao.FriendRequestDAO;
import social.network.jpa.dao.UserDAO;
import social.network.jpa.entities.UserEntity;
import social.network.jpa.entities.ids.FriendEntityId;
import social.network.jpa.entities.ids.FriendRequestEntityId;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CheckFriendRequestsDAServiceImpl implements CheckFriendRequestsDAService {
    private FriendRequestDAO friendRequestDAO;
    private UserDAO userDAO;

    @Override
    public List<HumanUser> findFriendRequestToUser(int idUser) {
        List<Integer> idsAllFriendRequests = friendRequestDAO.findAllToUser(idUser);
        List<UserEntity> users = userDAO.findAllByIds(idsAllFriendRequests);
        return users.stream().map(UserEntity::getHumanUser).toList();
    }
}
