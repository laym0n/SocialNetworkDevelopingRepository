package social.network.implbllservices.usersservices;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import social.network.dao.FriendDAO;
import social.network.daservices.ManageFriendsDAService;
import social.network.exceptions.EntityNotFoundException;

@Service
@AllArgsConstructor
public class ManageFriendsDAServiceImpl implements ManageFriendsDAService {
    private FriendDAO friendDAO;
    @Override
    public void deleteFriendRelationShipWithUsers(int idUserFriend, int idUserSenderRequest) {
        int countRowsDeleted = friendDAO.deleteFriendWithUsers(idUserFriend, idUserSenderRequest);
        if (countRowsDeleted == 0) {
            throw new EntityNotFoundException("Friend Relationship of users with ids " + idUserFriend
                    + " and "  + idUserSenderRequest + " do not exist");
        }
    }
}
