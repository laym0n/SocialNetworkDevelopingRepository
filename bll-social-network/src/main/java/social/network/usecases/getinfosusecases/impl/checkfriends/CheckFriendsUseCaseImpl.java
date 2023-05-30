package social.network.usecases.getinfosusecases.impl.checkfriends;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import social.network.daservices.CheckFriendsDAService;
import social.network.dto.requests.CheckFriendsRequest;
import social.network.dto.responses.CheckFriendsResponse;
import social.network.usecases.getinfosusecases.CheckFriendsUseCase;

@Service
@AllArgsConstructor
public class CheckFriendsUseCaseImpl implements CheckFriendsUseCase {
    private CheckFriendsDAService daService;
    @Override
    public CheckFriendsResponse checkFriends(CheckFriendsRequest request) {
        return new CheckFriendsResponse(daService.findFriendsInfoForUser(request.getIdUserSenderRequest()));
    }
}
