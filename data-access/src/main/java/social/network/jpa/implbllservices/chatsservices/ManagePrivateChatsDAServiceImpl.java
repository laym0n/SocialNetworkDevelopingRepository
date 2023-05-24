package social.network.jpa.implbllservices.chatsservices;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import social.network.daservices.ManagePrivateChatsDAService;
import social.network.entities.chat.changeshistory.chatchangeevents.ChatCreatedEvent;
import social.network.entities.chat.changeshistory.chatchangeevents.ChatDeletedEvent;
import social.network.entities.chat.changeshistory.chatchangeevents.ChatInfoChangedEvent;
import social.network.entities.chat.chatimpl.privatechat.PrivateChatInfo;
import social.network.jpa.entities.*;
import social.network.jpa.jpadao.*;

@Service
@AllArgsConstructor
public class ManagePrivateChatsDAServiceImpl implements ManagePrivateChatsDAService {
    private JPAPrivateChatDAO JPAPrivateChatDAO;
    private JPAChatTypeDAO JPAChatTypeDAO;
    private JPAEventDAO JPAEventDAO;
    private JPAEventTypeDAO JPAEventTypeDAO;
    private JPAChatMemberDAO JPAChatMemberDAO;

    @Override
    @Transactional
    public void createPrivateChatForUserAndSaveEvent(int idUser, PrivateChatInfo privateChatInfo, ChatCreatedEvent event) {
        PrivateChatEntity newChat = new PrivateChatEntity();
        Integer idType = JPAChatTypeDAO.findIdByName("PRIVATE_CHAT");
        newChat.setType(new ChatTypeEntity(idType, "PRIVATE_CHAT"));
        newChat.setName(privateChatInfo.getName());
        newChat.setUserId(idUser);
        JPAPrivateChatDAO.save(newChat);
        ChatMemberEntity chatMemberEntity = ChatMemberEntity
                .builder()
                .chatId(newChat.getId())
                .lastOrderIdOfCheckedMessage(0)
                .userId(idUser)
                .orderIdOfMessageWhereStartReading(0)
                .build();
        JPAChatMemberDAO.save(chatMemberEntity);
        Integer idEventType = JPAEventTypeDAO.findIdByName("CHAT_CREATED");
        EventEntity eventEntity = new EventEntity(
                new PrimaryKeyEventEntity(0, newChat.getId()),
                null,
                null,
                new EventTypeEntity(idEventType, "CHAT_CREATED"));
        JPAEventDAO.save(eventEntity);
    }

    @Override
    @Transactional
    public void deletePrivateChatOfUserAndSaveEvent(int idPrivateChat, int idUserSenderRequest, ChatDeletedEvent event) {

        JPAChatMemberDAO.deleteByChatIdAndUserId(idPrivateChat, idUserSenderRequest);
        JPAPrivateChatDAO.deleteByIdAndUserId(idPrivateChat, idUserSenderRequest);
    }

    @Override
    public void updatePrivatChatInfoOfUserAndSaveEvent(int idUserSender, int chatId, PrivateChatInfo privateChatInfo, ChatInfoChangedEvent event) {
        JPAPrivateChatDAO.updateNameByIdAndUserId(privateChatInfo.getName(), chatId, idUserSender);
        EventEntity chatInfoChangedEvent = new EventEntity(new PrimaryKeyEventEntity(0, chatId),
                null,
                null,
                JPAEventTypeDAO.findByName("CHAT_INFO_CHANGED").get()
        );
        JPAEventDAO.save(chatInfoChangedEvent);
    }
}
