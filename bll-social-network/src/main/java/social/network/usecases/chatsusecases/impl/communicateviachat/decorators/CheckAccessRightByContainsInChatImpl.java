package social.network.usecases.chatsusecases.impl.communicateviachat.decorators;

import social.network.daservices.CheckAccessRightByContainsInChatDAService;
import social.network.dto.requests.chat.*;
import social.network.usecases.chatsusecases.ManageGroupChatsUseCase;

import java.security.InvalidParameterException;

public class CheckAccessRightByContainsInChatImpl implements ManageGroupChatsUseCase {
    private CheckAccessRightByContainsInChatDAService daService;
    private ManageGroupChatsUseCase manageGroupChatsUseCase;

    @Override
    public void createChat(CreateGroupChatRequest request) {
        manageGroupChatsUseCase.createChat(request);
    }

//    @Override
//    public void addChatMember(AddChatMemberRequest request) {
//        checkIfUserIsInChat(request.getIdChat(), request.getIdUserSenderRequest());
//        checkIfUserIsNotInChat(request.getIdChat(), request.getIdUserNewMember());
//        manageGroupChatsUseCase.addChatMember(request);
//    }
//
//    @Override
//    public void leaveFromChat(LeaveFromChatRequest request) {
//        checkIfUserIsInChat(request.getIdChat(), request.getIdUserSenderRequest());
////        checkIfUserIsInChat(request.getIdChat(), request.getIdMemberForDelete());
//        manageGroupChatsUseCase.leaveFromChat(request);
//    }
//
//    @Override
//    public void editChatMember(EditChatMemberProfileRequest request) {
//        checkIfUserIsInChat(request.getIdChat(), request.getIdChatMemberForEdit());
//        checkIfUserIsInChat(request.getIdChat(), request.getIdMemberRequestOwner());
//        manageGroupChatsUseCase.editChatMember(request);
//    }
//
//    @Override
//    public void editChatInfo(UpdateAvatarGroupChatRequest request) {
//        checkIfUserIsInChat(request.getIdChat(), request.getIdUserSenderRequest());
//        manageGroupChatsUseCase.editChatInfo(request);
//    }

    private void checkIfUserIsInChat(int idChat, int idUser) {
        boolean isUserInChat = daService.isChatContainsChatMember(idChat, idUser);
        if (!isUserInChat) {
            throw new InvalidParameterException("User with id " + idUser + " is not in chat with id " + idChat);
        }
    }

    private void checkIfUserIsNotInChat(int idChat, int idUser) {
        boolean isUserInChat = daService.isChatContainsChatMember(idChat, idUser);
        if (isUserInChat) {
            throw new InvalidParameterException("User with id " + idUser + " is in chat with id " + idChat);
        }
    }
}
