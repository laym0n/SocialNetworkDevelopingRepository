package social.network.daservices;

import social.network.dto.ChatDescriptionDTO;
import social.network.dto.modelsdto.messages.MessageDTO;

import java.util.List;

public interface GetChatsDAService {
    List<ChatDescriptionDTO> findAllDescriptionsForUser(int userIdSenderRequest);

    List<MessageDTO> findAllMessageDTOForChat(int idChat);

    ChatDescriptionDTO findDescriptionForChat(int idChat, int idUser);

    boolean isFrozenDialogChat(int idChat);
}
