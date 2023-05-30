package social.network.usecases.getinfosusecases.impl.checkfriendrequests;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import social.network.daservices.CheckFriendRequestsDAService;
import social.network.dto.requests.CheckFriendRequestsRequest;
import social.network.dto.responses.CheckFriendRequestsResponse;
import social.network.usecases.getinfosusecases.CheckFriendRequestsUseCase;

@Service
@AllArgsConstructor
public class CheckFriendRequestsUseCaseImpl implements CheckFriendRequestsUseCase {
    private CheckFriendRequestsDAService daService;
    @Override
    public CheckFriendRequestsResponse checkFriendRequests(CheckFriendRequestsRequest request) {
        return new CheckFriendRequestsResponse(daService.findFriendRequestToUser(request.getIdUserSenderRequest()));
    }
}
