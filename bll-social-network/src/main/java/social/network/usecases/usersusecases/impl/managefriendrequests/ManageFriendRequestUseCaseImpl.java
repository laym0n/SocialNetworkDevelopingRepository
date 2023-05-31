package social.network.usecases.usersusecases.impl.managefriendrequests;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import social.network.daservices.ManageFriendsRequestsDAService;
import social.network.dto.requests.AcceptFriendRequestRequest;
import social.network.dto.requests.CancelFriendRequestRequest;
import social.network.dto.requests.SendNewFriendRequestRequest;
import social.network.exceptions.EntityAlreadyExistsException;
import social.network.exceptions.UserInBlackListException;
import social.network.usecases.usersusecases.ManageFriendRequestsUseCase;
import social.network.usecases.usersusecases.impl.managefriends.ManageFriendsUseCaseImpl;

import java.security.InvalidParameterException;
import java.util.logging.Logger;

@Service
@AllArgsConstructor
public class ManageFriendRequestUseCaseImpl implements ManageFriendRequestsUseCase {
    private static Logger log = Logger.getLogger(ManageFriendRequestUseCaseImpl.class.getName());
    private ManageFriendsRequestsDAService daService;
    @Override
    public void sendNewRequest(SendNewFriendRequestRequest request) {
        log.info("Get SendNewFriendRequestRequest " + request);
        checkIfFriendRequestIsForYourself(request.getIdUserSenderRequest(), request.getIdUserForWhomRequest());
        boolean isFriendRelationshipAlreadyExist = daService
                .isExistFriendRelationship(request.getIdUserSenderRequest(), request.getIdUserForWhomRequest());
        if(isFriendRelationshipAlreadyExist) {
            throw new EntityAlreadyExistsException("Users already is friends");
        }
        boolean isUsersInBlackList = daService
                .isBlackListRelationshipExistBetweenUsers(
                        request.getIdUserSenderRequest(),
                        request.getIdUserForWhomRequest()
                );
        if (isUsersInBlackList) {
            throw new UserInBlackListException("Users is in black list of each other");
        }
        daService.createFriendRequestAndCheckIfAlreadyExists(request.getFriendRequest());
    }

    @Override
    public void acceptRequest(AcceptFriendRequestRequest request) {
        daService.checkAndDeleteFriendRequestAndCreateFriend(request.getFriendRequest());
    }

    @Override
    public void cancelRequest(CancelFriendRequestRequest request) {
        daService.deleteFriendRequest(request.getFriendRequest());
    }
    private void checkIfFriendRequestIsForYourself(int idFirstUser, int idSecondUser) {
        if (idSecondUser == idFirstUser) {
            throw new InvalidParameterException("Friend Request can not be sent for yourself");
        }
    }
}
