package social.network.jpa.implbllservices.usersservices;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import social.network.jpa.dao.FriendDAO;
import social.network.daservices.ManageFriendsDAService;
import social.network.exceptions.EntityNotFoundException;

@Service
@AllArgsConstructor
public class JPAManageFriendsDAService implements ManageFriendsDAService {
    private FriendDAO friendDAO;
    @Transactional
    @Override
    public void deleteFriendRelationShipWithUsers(int idUserFriend, int idUserSenderRequest) {
        int countRowsDeleted = friendDAO.deleteFriendWithUsers(idUserFriend, idUserSenderRequest);
        if (countRowsDeleted == 0) {
            throw new EntityNotFoundException("Friend Relationship of users with ids " + idUserFriend
                    + " and "  + idUserSenderRequest + " do not exist");
        }
    }
}
