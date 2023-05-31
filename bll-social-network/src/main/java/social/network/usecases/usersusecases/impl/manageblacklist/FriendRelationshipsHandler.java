package social.network.usecases.usersusecases.impl.manageblacklist;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import social.network.daservices.FriendRelationshipsHandlerDAService;
import social.network.dto.requests.AddUserToBlackListRequest;
import social.network.dto.requests.RemoveUserFromBlackListRequest;
import social.network.usecases.usersusecases.ManageBlackListUseCase;

import java.util.logging.Logger;

@Component
@AllArgsConstructor
public class FriendRelationshipsHandler implements ManageBlackListUseCase {
    private static Logger log = Logger.getLogger(FriendRelationshipsHandler.class.getName());
    private FriendRelationshipsHandlerDAService daService;
    private ManageBlackListUseCase manageBlackListUseCase;

    @Override
    public void addUserToBlackList(AddUserToBlackListRequest request) {
        manageBlackListUseCase.addUserToBlackList(request);
        log.info("Removing FriendRelationship and FriendRequests after adding user to black list");
        daService.deleteFriendRelationshipAndFriendRequestIfExist(
                request.getIdUserForAddToBlackList(),
                request.getIdUserSenderRequest()
        );
    }

    @Override
    public void removeFromBlackList(RemoveUserFromBlackListRequest request) {
        manageBlackListUseCase.removeFromBlackList(request);
    }
}
