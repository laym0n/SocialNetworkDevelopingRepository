package social.network.implbllservices.chatsservices;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import social.network.dao.*;
import social.network.daservices.ManagePrivateChatsDAService;
import social.network.entities.*;
import social.network.entities.chat.changeshistory.chatchangeevents.ChatCreatedEvent;
import social.network.entities.chat.changeshistory.chatchangeevents.ChatDeletedEvent;
import social.network.entities.chat.changeshistory.chatchangeevents.ChatInfoChangedEvent;
import social.network.entities.chat.chatimpl.privatechat.PrivateChatInfo;

@Service
@AllArgsConstructor
public class ManagePrivateChatsDAServiceImpl implements ManagePrivateChatsDAService {
    private PrivateChatDAO privateChatDAO;
    private ChatTypeDAO chatTypeDAO;
    private EventDAO eventDAO;
    private EventTypeDAO eventTypeDAO;
    private ChatMemberDAO chatMemberDAO;

    @Override
    @Transactional
    public void createPrivateChatForUserAndSaveEvent(int idUser, PrivateChatInfo privateChatInfo, ChatCreatedEvent event) {
        PrivateChatEntity newChat = new PrivateChatEntity();
        Integer idType = chatTypeDAO.findIdByName("PRIVATE_CHAT");
        newChat.setType(new ChatTypeEntity(idType, "PRIVATE_CHAT"));
        newChat.setName(privateChatInfo.getName());
        newChat.setUserId(idUser);
        privateChatDAO.save(newChat);
        ChatMemberEntity chatMemberEntity = ChatMemberEntity
                .builder()
                .chatId(newChat.getId())
                .lastOrderIdOfCheckedMessage(0)
                .userId(idUser)
                .orderIdOfMessageWhereStartReading(0)
                .build();
        chatMemberDAO.save(chatMemberEntity);
        Integer idEventType = eventTypeDAO.findIdByName("CHAT_CREATED");
        EventEntity eventEntity = new EventEntity(
                new PrimaryKeyEventEntity(0, newChat.getId()),
                null,
                null,
                new EventTypeEntity(idEventType, "CHAT_CREATED"));
        eventDAO.save(eventEntity);
    }

    @Override
    @Transactional
    public void deletePrivateChatOfUserAndSaveEvent(int idPrivateChat, int idUserSenderRequest, ChatDeletedEvent event) {

        chatMemberDAO.deleteByChatIdAndUserId(idPrivateChat, idUserSenderRequest);
        privateChatDAO.deleteByIdAndUserId(idPrivateChat, idUserSenderRequest);
    }

    @Override
    public void updatePrivatChatInfoOfUserAndSaveEvent(int idUserSender, int chatId, PrivateChatInfo privateChatInfo, ChatInfoChangedEvent event) {
        privateChatDAO.updateNameByIdAndUserId(privateChatInfo.getName(), chatId, idUserSender);
        EventEntity chatInfoChangedEvent = new EventEntity(new PrimaryKeyEventEntity(0, chatId),
                null,
                null,
                eventTypeDAO.findByName("CHAT_INFO_CHANGED").get()
        );
        eventDAO.save(chatInfoChangedEvent);
    }
}
