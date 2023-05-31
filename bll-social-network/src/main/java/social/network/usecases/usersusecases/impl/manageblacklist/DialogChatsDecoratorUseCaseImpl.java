package social.network.usecases.usersusecases.impl.manageblacklist;

import lombok.AllArgsConstructor;
import social.network.daservices.DialogChatsDecoratorDAService;
import social.network.dto.requests.AddUserToBlackListRequest;
import social.network.dto.requests.RemoveUserFromBlackListRequest;
import social.network.usecases.usersusecases.ManageBlackListUseCase;
import social.network.usecases.usersusecases.impl.managefriendrequests.ManageFriendRequestUseCaseImpl;

import java.util.logging.Logger;

@AllArgsConstructor
public class DialogChatsDecoratorUseCaseImpl implements ManageBlackListUseCase {
    private static Logger log = Logger.getLogger(DialogChatsDecoratorUseCaseImpl.class.getName());
    private DialogChatsDecoratorDAService daService;
    private ManageBlackListUseCase manageBlackListUseCase;
    @Override
    public void addUserToBlackList(AddUserToBlackListRequest request) {
        manageBlackListUseCase.addUserToBlackList(request);
        log.info("Change isFrozen for dialog chat after adding user to black list");
        daService.setIsFrozenDueToBlackListIfExists(request.getIdUserForAddToBlackList(),
                request.getIdUserSenderRequest(),
                true);
    }

    @Override
    public void removeFromBlackList(RemoveUserFromBlackListRequest request) {
        manageBlackListUseCase.removeFromBlackList(request);
        log.info("Change isFrozen for dialog chat after removing user to black list");
        daService.setIsFrozenDueToBlackListIfExists(request.getIdUserForRemoveFromBlackList(),
                request.getIdUserSenderRequest(),
                false);
    }
}
