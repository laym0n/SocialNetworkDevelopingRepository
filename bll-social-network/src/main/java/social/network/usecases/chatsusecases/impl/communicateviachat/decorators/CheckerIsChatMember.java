package social.network.usecases.chatsusecases.impl.communicateviachat.decorators;

import social.network.daservices.ChecerIsChatMemberDAService;
import social.network.daservices.CommunicateViaChatDAService;
import social.network.dto.requests.*;
import social.network.usecases.chatsusecases.CommunicateViaGroupChatUseCase;

import java.security.InvalidParameterException;

public class CheckerIsChatMember implements CommunicateViaGroupChatUseCase {
    private ChecerIsChatMemberDAService daService;
    private CommunicateViaGroupChatUseCase communicateViaGroupChatUseCase;
    @Override
    public void sendMessage(SendMessageRequest request) {
        communicateViaGroupChatUseCase.sendMessage(request);
    }

    @Override
    public void deleteMessage(DeleteMessageRequest request) {
        communicateViaGroupChatUseCase.deleteMessage(request);
    }

    @Override
    public void editMessage(EditMessageRequest request) {
        communicateViaGroupChatUseCase.editMessage(request);
    }

    @Override
    public void clearMessageHistory(ClearMessageHistoryRequest request) {
        communicateViaGroupChatUseCase.clearMessageHistory(request);
    }

    @Override
    public void deleteMessageOnlyForUser(DeleteMessageOnlyForUserRequest request) {
        communicateViaGroupChatUseCase.deleteMessageOnlyForUser(request);
    }
    private void checkIfUserIsChatMemberOfChat(int idUser, int idChat) {
        boolean isChatMember = daService.isUserIsChatMemberOfChat(idUser, idChat);
        if(!isChatMember) {
            throw new InvalidParameterException("User with id " + idUser
                + " is not chat member of chat with id " + idChat);
        }
    }
}
