package social.network.usecases.usersusecases;

import social.network.dto.requests.AcceptFriendRequestRequest;
import social.network.dto.requests.CancelFriendRequestRequest;
import social.network.dto.requests.SendNewFriendRequestRequest;

public interface ManageFriendRequestsUseCase {
    void sendNewRequest(SendNewFriendRequestRequest request);
    void acceptRequest(AcceptFriendRequestRequest request);
    void cancelRequest(CancelFriendRequestRequest request);
}
