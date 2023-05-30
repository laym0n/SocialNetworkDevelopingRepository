package social.network.usecases.usersusecases.impl.manageblacklist;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import social.network.daservices.DialogChatsDecoratorDAService;
import social.network.daservices.ManageBlackListDAService;
import social.network.usecases.usersusecases.ManageBlackListUseCase;

@Component
@AllArgsConstructor
public class ManageBlackListFactoryImpl implements FactoryManageBlackList{
    private DialogChatsDecoratorDAService dialogChatsDecoratorDAService;
    private ManageBlackListDAService manageBlackListDAService;
    private HandlerAddingToBlackList handlerAddingToBlackList;
    @Override
    public ManageBlackListUseCase getManageBlackListUseCase() {
        ManageBlackListUseCaseImpl manageBlackListUseCase =
                new ManageBlackListUseCaseImpl(
                        manageBlackListDAService,
                        handlerAddingToBlackList
                );
        DialogChatsDecoratorUseCaseImpl dialogChatsDecoratorUseCase =
                new DialogChatsDecoratorUseCaseImpl(
                        dialogChatsDecoratorDAService,
                        manageBlackListUseCase
                );
        return dialogChatsDecoratorUseCase;
    }
}
