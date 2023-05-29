package social.network.jpa.implbllservices.chatsservices;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import social.network.daservices.ManagePrivateChatsDAService;
import social.network.entities.chat.changeshistory.chatchangeevents.ChatCreatedEvent;
import social.network.entities.chat.changeshistory.chatchangeevents.ChatDeletedEvent;
import social.network.entities.chat.chatimpl.privatechat.PrivateChat;
import social.network.exceptions.EntityNotFoundException;
import social.network.jpa.dao.*;
import social.network.jpa.entities.*;

@Service
@AllArgsConstructor
public class ManagePrivateChatsDAServiceImpl implements ManagePrivateChatsDAService {
    private static String PRIVATE_CHAT_TYPE_COLUMN_VALUE = "PRIVATE_CHAT";
    private ChatTypeDAO chatTypeDAO;
    private ChatDAO chatDAO;
    private ChatMemberDAO chatMemberDAO;
    private EventDAO eventDAO;
    private EventTypeDAO eventTypeDAO;

    @Override
    @Transactional
    public void createPrivateChatForUserAndSaveEvent(int idUser, PrivateChat newPrivateChat, ChatCreatedEvent event) {
        ChatEntity newChatEntity = new ChatEntity(newPrivateChat);
        Integer idType = chatTypeDAO.findIdByName(PRIVATE_CHAT_TYPE_COLUMN_VALUE);
        newChatEntity.setType(new ChatTypeEntity(idType, PRIVATE_CHAT_TYPE_COLUMN_VALUE));
        chatDAO.create(newChatEntity);
        ChatMemberEntity chatMemberEntity = ChatMemberEntity
                .builder()
                .chatId(newChatEntity.getId())
                .lastOrderIdOfCheckedMessage(0)
                .userId(idUser)
                .isBlocked(false)
                .lastOrderIdOfCheckedMessage(0)
                .groupMemberName(null)
                .orderIdOfMessageWhereStartReading(0)
                .build();
        chatMemberDAO.create(chatMemberEntity);
        Integer idEventType = eventTypeDAO.findIdByName("CHAT_CREATED");
        EventEntity eventEntity = new EventEntity(
                new PrimaryKeyEventEntity(0, newChatEntity.getId()),
                null,
                null,
                new EventTypeEntity(idEventType, "CHAT_CREATED"));
        eventDAO.create(eventEntity);
        newPrivateChat.setId(newChatEntity.getId());
    }

    @Override
    @Transactional
    public void SaveEventForChatAndSetNotActiveChat(int idChat, ChatDeletedEvent event) {
        Integer idEventType = eventTypeDAO.findIdByName("CHAT_DELETED");
        EventEntity eventEntity = new EventEntity(
                new PrimaryKeyEventEntity(0, idChat),
                null,
                null,
                new EventTypeEntity(idEventType, "CHAT_DELETED"));
        eventDAO.create(eventEntity);
        chatDAO.updateIsActiveForChat(idChat, false);
    }
}
