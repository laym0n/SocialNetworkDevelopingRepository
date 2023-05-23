package social.network.usecases.getinfosusecases;

import social.network.dto.requests.CheckFriendsRequest;
import social.network.dto.responses.CheckFriendsResponse;

public interface CheckFriendsUseCase {
    CheckFriendsResponse checkFriends(CheckFriendsRequest request);
}
