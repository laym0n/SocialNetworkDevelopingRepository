package social.network.usecases.usersusecases.impl.managefriends;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import social.network.daservices.ManageFriendsDAService;
import social.network.dto.requests.DeleteFriendRequest;
import social.network.usecases.usersusecases.ManageFriendsUseCase;
import social.network.usecases.usersusecases.impl.signup.SignUpUseCaseImpl;

import java.util.logging.Logger;

@Service
@AllArgsConstructor
public class ManageFriendsUseCaseImpl implements ManageFriendsUseCase {
    private static Logger log = Logger.getLogger(ManageFriendsUseCaseImpl.class.getName());
    private ManageFriendsDAService daService;
    @Override
    public void deleteFriend(DeleteFriendRequest request) {
        log.info("Get DeleteFriendRequest" + request);
        daService.deleteFriendRelationShipWithUsers(request.getIdUserFriend(), request.getIdUserSenderRequest());
    }
}
