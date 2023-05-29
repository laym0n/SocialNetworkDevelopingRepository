package social.network.jpa.implbllservices.chatsservices;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import social.network.daservices.ManageDialogsChatsDAService;
import social.network.entities.chat.changeshistory.chatchangeevents.ChatCreatedEvent;
import social.network.entities.chat.messages.Message;
import social.network.jpa.dao.*;
import social.network.jpa.entities.*;

@Service
@AllArgsConstructor
public class ManageDialogsChatsDAServiceImpl implements ManageDialogsChatsDAService {
    private static final String DIALOG_CHAT_TYPE_COLUMN_VALUE = "DIALOG_CHAT";
    private UsersBlackListDAO usersBlackListDAO;
    private ChatMemberDAO chatMemberDAO;
    private MessageDAO messageDAO;
    private ChatDAO chatDAO;
    private ChatTypeDAO chatTypeDAO;
    private EventTypeDAO eventTypeDAO;
    private EventDAO eventDAO;

    @Override
    public boolean checkIfUsersInBlackListsEachOther(int firstIdUser, int secondIdUser) {
        return usersBlackListDAO.existsAnyWithUsers(firstIdUser, secondIdUser);
    }

    @Override
    public void saveDialogChatWithMessageAndSaveEvent(Message message,
                                                            int idFirstUser,
                                                            int idSecondUser,
                                                            ChatCreatedEvent event) {
        ChatEntity newChatEntity = ChatEntity
                .builder()
                .isBlocked(false)
                .isActive(true)
                .isFrozenDueToBlacklist(false)
                .type(chatTypeDAO.findByName(DIALOG_CHAT_TYPE_COLUMN_VALUE))
                .build();
        chatDAO.create(newChatEntity);
        ChatMemberEntity firstChatMemberEntity = ChatMemberEntity
                .builder()
                .chatId(newChatEntity.getId())
                .lastOrderIdOfCheckedMessage(0)
                .userId(idFirstUser)
                .isBlocked(false)
                .lastOrderIdOfCheckedMessage(0)
                .groupMemberName(null)
                .orderIdOfMessageWhereStartReading(0)
                .build();
        chatMemberDAO.create(firstChatMemberEntity);
        ChatMemberEntity secondChatMemberEntity = ChatMemberEntity
                .builder()
                .chatId(newChatEntity.getId())
                .lastOrderIdOfCheckedMessage(0)
                .userId(idSecondUser)
                .isBlocked(false)
                .lastOrderIdOfCheckedMessage(0)
                .groupMemberName(null)
                .orderIdOfMessageWhereStartReading(0)
                .build();
        chatMemberDAO.create(secondChatMemberEntity);
        Integer idEventType = eventTypeDAO.findIdByName("CHAT_CREATED");
        EventEntity eventEntity = new EventEntity(
                new PrimaryKeyEventEntity(0, newChatEntity.getId()),
                null,
                null,
                new EventTypeEntity(idEventType, "CHAT_CREATED"));
        eventDAO.create(eventEntity);
    }
}
