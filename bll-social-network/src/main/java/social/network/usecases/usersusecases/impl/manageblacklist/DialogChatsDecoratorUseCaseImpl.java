package social.network.usecases.usersusecases.impl.manageblacklist;

import lombok.AllArgsConstructor;
import social.network.daservices.DialogChatsDecoratorDAService;
import social.network.dto.requests.AddUserToBlackListRequest;
import social.network.dto.requests.RemoveUserFromBlackListRequest;
import social.network.usecases.usersusecases.ManageBlackListUseCase;

@AllArgsConstructor
public class DialogChatsDecoratorUseCaseImpl implements ManageBlackListUseCase {
    private DialogChatsDecoratorDAService daService;
    private ManageBlackListUseCase manageBlackListUseCase;
    @Override
    public void addUserToBlackList(AddUserToBlackListRequest request) {
        manageBlackListUseCase.addUserToBlackList(request);
        daService.setIsFrozenDueToBlackListIfExists(request.getIdUserForAddToBlackList(),
                request.getIdUserSenderRequest(),
                true);
    }

    @Override
    public void removeFromBlackList(RemoveUserFromBlackListRequest request) {
        manageBlackListUseCase.removeFromBlackList(request);
        daService.setIsFrozenDueToBlackListIfExists(request.getIdUserForRemoveFromBlackList(),
                request.getIdUserSenderRequest(),
                false);
    }
}
