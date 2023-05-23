package social.network.usecases.usersusecases.impl.managefriends;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import social.network.daservices.ManageFriendsDAService;
import social.network.dto.requests.DeleteFriendRequest;
import social.network.usecases.usersusecases.ManageFriendsUseCase;

@Service
@AllArgsConstructor
public class ManageFriendsUseCaseImpl implements ManageFriendsUseCase {
    private ManageFriendsDAService daService;
    @Override
    public void deleteFriend(DeleteFriendRequest request) {
        daService.deleteFriendRelationShipWithUsers(request.getIdUserFriend(), request.getIdUserSenderRequest());
    }
}
