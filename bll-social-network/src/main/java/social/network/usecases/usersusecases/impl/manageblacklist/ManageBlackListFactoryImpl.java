package social.network.usecases.usersusecases.impl.manageblacklist;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import social.network.daservices.DialogChatsDecoratorDAService;
import social.network.daservices.FriendRelationshipsHandlerDAService;
import social.network.daservices.ManageBlackListDAService;
import social.network.usecases.usersusecases.ManageBlackListUseCase;

@Component
@AllArgsConstructor
public class ManageBlackListFactoryImpl implements FactoryManageBlackList {
    private DialogChatsDecoratorDAService dialogChatsDecoratorDAService;
    private ManageBlackListDAService manageBlackListDAService;
    private FriendRelationshipsHandlerDAService friendRelationshipsHandlerDAService;

    @Override
    public ManageBlackListUseCase getManageBlackListUseCase() {
        ManageBlackListUseCaseImpl manageBlackListUseCase =
                new ManageBlackListUseCaseImpl(
                        manageBlackListDAService
                );
        DialogChatsDecoratorUseCaseImpl dialogChatsDecoratorUseCase =
                new DialogChatsDecoratorUseCaseImpl(
                        dialogChatsDecoratorDAService,
                        manageBlackListUseCase
                );
        FriendRelationshipsHandler friendRelationshipsHandler =
                new FriendRelationshipsHandler(
                        friendRelationshipsHandlerDAService,
                        dialogChatsDecoratorUseCase
                );
        return friendRelationshipsHandler;
    }
}
