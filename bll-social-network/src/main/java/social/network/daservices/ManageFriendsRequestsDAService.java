package social.network.daservices;

import social.network.entities.usersrelationships.friends.FriendRequest;

public interface ManageFriendsRequestsDAService {
    void saveFriendRequestAndCheckIfAlreadyExists(FriendRequest friendRequest);

    void deleteFriendRequest(FriendRequest friendRequest);

    void checkAndDeleteFriendRequestAndCreateFriend(FriendRequest friendRequest);

    boolean isExistFriendRelationship(int firstUser, int secondUser);

    boolean isBlackListRelationshipExistBetweenUsers(int firstUser, int secondUser);
}
