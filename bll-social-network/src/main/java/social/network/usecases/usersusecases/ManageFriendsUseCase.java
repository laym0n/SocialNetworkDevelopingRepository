package social.network.usecases.usersusecases;

import social.network.dto.requests.DeleteFriendRequest;

public interface ManageFriendsUseCase {
    void deleteFriend(DeleteFriendRequest request);
}
