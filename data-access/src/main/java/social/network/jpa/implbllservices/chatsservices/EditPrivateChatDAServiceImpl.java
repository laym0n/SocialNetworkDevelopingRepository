package social.network.jpa.implbllservices.chatsservices;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import social.network.daservices.EditPrivateChatDAService;
import social.network.entities.chat.changeshistory.chatchangeevents.ChatInfoChangedEvent;
import social.network.entities.chat.chatimpl.privatechat.PrivateChatDescription;
import social.network.jpa.dao.*;
import social.network.jpa.entities.EventEntity;
import social.network.jpa.entities.EventTypeEntity;
import social.network.jpa.entities.PrimaryKeyEventEntity;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class EditPrivateChatDAServiceImpl implements EditPrivateChatDAService {
    private ChatDAO chatDAO;
    private EventDAO eventDAO;
    private EventTypeDAO eventTypeDAO;
    @Override
    public void updatePrivateChatDescriptionOfUserAndSaveEvent(int idChat,
                                                               PrivateChatDescription newDescription,
                                                               ChatInfoChangedEvent event) {
        chatDAO.updateDescriptionAndName(
                idChat,
                newDescription.getName(),
                newDescription.getDescription().orElse(null)
        );
        saveChatInfoChangedEvent(idChat);
    }

    @Override
    public void updatePrivateChatAvatarOfUserAndSaveEvent(int idChat,
                                                          Optional<byte[]> newAvatar,
                                                          ChatInfoChangedEvent event) {
        chatDAO.updateAvatar(idChat, newAvatar.orElse(null));
        saveChatInfoChangedEvent(idChat);
    }
    private void saveChatInfoChangedEvent(int idChat) {
        Integer idEventType = eventTypeDAO.findIdByName("CHAT_INFO_CHANGED");
        EventEntity eventEntity = new EventEntity(
                new PrimaryKeyEventEntity(0, idChat),
                null,
                null,
                new EventTypeEntity(idEventType, "CHAT_INFO_CHANGED"));
        eventDAO.create(eventEntity);
    }
}
