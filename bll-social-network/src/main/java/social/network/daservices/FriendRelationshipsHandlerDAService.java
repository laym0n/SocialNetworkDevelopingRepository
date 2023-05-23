package social.network.daservices;

public interface FriendRelationshipsHandlerDAService {
    void deleteFriendRelationshipAndFriendRequestIfExist(int firstUserId, int secondUserId);
}
