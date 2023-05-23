package social.network.implbllservices.usersservices;

import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import social.network.dao.FriendDAO;
import social.network.dao.FriendRequestDAO;
import social.network.dao.UsersBlackListDAO;
import social.network.daservices.ManageFriendsRequestsDAService;
import social.network.entities.FriendEntity;
import social.network.entities.FriendRequestEntity;
import social.network.entities.ids.FriendRequestEntityId;
import social.network.entities.usersrelationships.friends.FriendRequest;
import social.network.exceptions.EntityAlreadyExistsException;
import social.network.exceptions.EntityNotFoundException;

@Service
@AllArgsConstructor
public class ManageFriendRequestsDAServiceImpl implements ManageFriendsRequestsDAService {
    private FriendRequestDAO friendRequestDAO;
    private FriendDAO friendDAO;
    private UsersBlackListDAO usersBlackListDAO;

    @Override
    public void saveFriendRequestAndCheckIfAlreadyExists(FriendRequest friendRequest) {
        boolean isRequestExistWithUsers = friendRequestDAO.isExistWithUsers(
                friendRequest.getIdUserToWhom(),
                friendRequest.getIdUserSender()
        );
        if(isRequestExistWithUsers){
            throw new EntityAlreadyExistsException("FriendRequest from user with id "
                    + friendRequest.getIdUserSender() + " to user with id "
                    + friendRequest.getIdUserToWhom() + " already exists");
        }
        FriendRequestEntity newFriendRequestEntity = new FriendRequestEntity(friendRequest);
        friendRequestDAO.save(newFriendRequestEntity);
    }

    @Override
    public void deleteFriendRequest(FriendRequest friendRequest) {
        friendRequestDAO.deleteAllById(
                new FriendRequestEntityId(
                        friendRequest.getIdUserSender(),
                        friendRequest.getIdUserToWhom())
        );
    }

    @Override
    public void checkAndDeleteFriendRequestAndCreateFriend(FriendRequest friendRequest) {
        boolean isRequestExist = friendRequestDAO.existsById(
                new FriendRequestEntityId(
                        friendRequest.getIdUserSender(),
                        friendRequest.getIdUserToWhom()
                )
        );
        if (!isRequestExist) {
            throw new EntityNotFoundException("Friend request from user with id "
                    + friendRequest.getWhenSent() + " to user with id "
                    + friendRequest.getIdUserToWhom() + " does not exist");
        }
        friendRequestDAO.deleteById(
                new FriendRequestEntityId(
                        friendRequest.getIdUserSender(),
                        friendRequest.getIdUserToWhom()
                )
        );
        FriendEntity newFriend = new FriendEntity(friendRequest);
        friendDAO.save(newFriend);
    }

    @Override
    public boolean isExistFriendRelationship(int firstUser, int secondUser) {
        return friendDAO.existsWithUserIds(firstUser, secondUser);
    }

    @Override
    public boolean isBlackListRelationshipExistBetweenUsers(int firstUser, int secondUser) {
        return usersBlackListDAO.existsAnyWithUsers(firstUser, secondUser);
    }
}
