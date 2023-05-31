package social.network.usecases.usersusecases.impl.manageblacklist;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import social.network.daservices.ManageBlackListDAService;
import social.network.dto.requests.AddUserToBlackListRequest;
import social.network.dto.requests.RemoveUserFromBlackListRequest;
import social.network.entities.usersrelationships.blacklist.BlackListRelationship;
import social.network.usecases.usersusecases.ManageBlackListUseCase;

import java.security.InvalidParameterException;
import java.util.logging.Logger;

@AllArgsConstructor
public class ManageBlackListUseCaseImpl implements ManageBlackListUseCase {
    private static Logger log = Logger.getLogger(ManageBlackListUseCaseImpl.class.getName());
    private ManageBlackListDAService daService;
    @Override
    public void addUserToBlackList(AddUserToBlackListRequest request) {
        log.info("Get AddUserToBlackListRequest " + request);
        checkIfUserIdsEqual(request.getIdUserForAddToBlackList(), request.getIdUserSenderRequest());
        BlackListRelationship newBlackListRelationship = request.getBlackListRelationship();
        daService.saveBlackListRelationshipAndCheckAlreadyExists(newBlackListRelationship);
    }

    @Override
    public void removeFromBlackList(RemoveUserFromBlackListRequest request) {
        log.info("Get RemoveUserFromBlackListRequest " + request);
        checkIfUserIdsEqual(request.getIdUserForRemoveFromBlackList(), request.getIdUserSenderRequest());
        daService.deleteBlackListRelationship(request.getBlackListRelationship());
    }

    private void checkIfUserIdsEqual(int idFirstUser, int idSecondUser){
        if(idFirstUser == idSecondUser){
            throw new InvalidParameterException("User can not add to black list himself");
        }
    }
}
