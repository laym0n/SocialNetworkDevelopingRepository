package social.network.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import social.network.daservices.CheckerOwnerPrivateChatDAService;
import social.network.services.CheckerOwnerPrivateChat;

import java.security.InvalidParameterException;

@Service
@AllArgsConstructor
public class CheckerOwnerPrivateChatImpl implements CheckerOwnerPrivateChat {
    private CheckerOwnerPrivateChatDAService daService;
    @Override
    public void checkIfUserIsOwnerOfPrivateChat(int idUser, int idChat) {
        Integer idOwnerChat = daService.getOwnerPrivateChat(idChat);
        if (!idOwnerChat.equals(idUser)) {
            throw new InvalidParameterException("User with id " + idUser
                    + " is not owner private chat with id " + idChat);
        }
    }
}
