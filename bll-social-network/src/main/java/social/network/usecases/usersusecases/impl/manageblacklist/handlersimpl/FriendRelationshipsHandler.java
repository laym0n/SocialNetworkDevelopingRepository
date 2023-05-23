package social.network.usecases.usersusecases.impl.manageblacklist.handlersimpl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import social.network.daservices.FriendRelationshipsHandlerDAService;
import social.network.entities.usersrelationships.blacklist.BlackListRelationship;
import social.network.usecases.usersusecases.impl.manageblacklist.HandlerAddingToBlackList;

@Component
@AllArgsConstructor
public class FriendRelationshipsHandler implements HandlerAddingToBlackList {
    private FriendRelationshipsHandlerDAService daService;
    @Override
    public void handle(BlackListRelationship blackListRelationship) {
        daService.deleteFriendRelationshipAndFriendRequestIfExist(
                blackListRelationship.getIdOwnerBlackList(),
                blackListRelationship.getIdBlockedUser()
        );
    }
}
