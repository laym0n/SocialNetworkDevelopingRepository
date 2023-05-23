package social.network.usecases.getinfosusecases;

import social.network.dto.requests.CheckFriendRequestsRequest;
import social.network.dto.responses.CheckFriendRequestsResponse;

public interface CheckFriendRequestsUseCase {
    CheckFriendRequestsResponse checkFriendRequests(CheckFriendRequestsRequest request);
}
