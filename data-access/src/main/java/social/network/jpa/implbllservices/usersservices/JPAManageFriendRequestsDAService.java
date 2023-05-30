package social.network.jpa.implbllservices.usersservices;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import social.network.jpa.dao.FriendDAO;
import social.network.jpa.dao.FriendRequestDAO;
import social.network.jpa.dao.UsersBlackListDAO;
import social.network.daservices.ManageFriendsRequestsDAService;
import social.network.jpa.entities.FriendEntity;
import social.network.jpa.entities.FriendRequestEntity;
import social.network.jpa.entities.ids.FriendRequestEntityId;
import social.network.entities.usersrelationships.friends.FriendRequest;
import social.network.exceptions.EntityAlreadyExistsException;
import social.network.exceptions.EntityNotFoundException;

@Service
@AllArgsConstructor
public class JPAManageFriendRequestsDAService implements ManageFriendsRequestsDAService {
    private FriendRequestDAO friendRequestDAO;
    private FriendDAO friendDAO;
    private UsersBlackListDAO usersBlackListDAO;

    @Override
    public void createFriendRequestAndCheckIfAlreadyExists(FriendRequest friendRequest) {
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
        friendRequestDAO.create(newFriendRequestEntity);
    }
    @Transactional
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
        friendDAO.create(newFriend);
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
