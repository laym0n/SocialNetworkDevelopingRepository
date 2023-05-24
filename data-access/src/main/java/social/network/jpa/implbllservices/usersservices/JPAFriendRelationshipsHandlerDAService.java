package social.network.jpa.implbllservices.usersservices;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import social.network.jpa.dao.FriendDAO;
import social.network.jpa.dao.FriendRequestDAO;
import social.network.daservices.FriendRelationshipsHandlerDAService;

@Service
@AllArgsConstructor
public class JPAFriendRelationshipsHandlerDAService implements FriendRelationshipsHandlerDAService {
    private FriendDAO friendDAO;
    private FriendRequestDAO friendRequestDAO;
    @Transactional
    @Override
    public void deleteFriendRelationshipAndFriendRequestIfExist(int firstUserId, int secondUserId) {
        friendDAO.deleteFriendWithUsers(firstUserId, secondUserId);
        friendRequestDAO.deleteAllByContainingUsers(firstUserId, secondUserId);
    }
}
