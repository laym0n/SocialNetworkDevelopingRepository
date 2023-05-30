package social.network.usecases.usersusecases.impl.manageblacklist;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import social.network.daservices.ManageBlackListDAService;
import social.network.dto.requests.AddUserToBlackListRequest;
import social.network.dto.requests.RemoveUserFromBlackListRequest;
import social.network.entities.usersrelationships.blacklist.BlackListRelationship;
import social.network.usecases.usersusecases.ManageBlackListUseCase;

import java.security.InvalidParameterException;

@AllArgsConstructor
public class ManageBlackListUseCaseImpl implements ManageBlackListUseCase {
    private ManageBlackListDAService daService;
    private HandlerAddingToBlackList handlerAddingToBlackList;
    @Override
    public void addUserToBlackList(AddUserToBlackListRequest request) {
        checkIfUserIdsEqual(request.getIdUserForAddToBlackList(), request.getIdUserSenderRequest());
        BlackListRelationship newBlackListRelationship = request.getBlackListRelationship();
        daService.saveBlackListRelationshipAndCheckAlreadyExists(newBlackListRelationship);
        handlerAddingToBlackList.handle(newBlackListRelationship);
    }

    @Override
    public void removeFromBlackList(RemoveUserFromBlackListRequest request) {
        checkIfUserIdsEqual(request.getIdUserForRemoveFromBlackList(), request.getIdUserSenderRequest());
        daService.deleteBlackListRelationship(request.getBlackListRelationship());
    }

    private void checkIfUserIdsEqual(int idFirstUser, int idSecondUser){
        if(idFirstUser == idSecondUser){
            throw new InvalidParameterException("User can not add to black list himself");
        }
    }
}
