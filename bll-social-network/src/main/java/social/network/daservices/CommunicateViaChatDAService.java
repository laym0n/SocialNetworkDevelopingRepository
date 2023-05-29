package social.network.daservices;

import social.network.entities.chat.messages.Message;
import social.network.entities.chat.changeshistory.chatchangeevents.ChatMessageDeletedEvent;
import social.network.entities.chat.changeshistory.chatchangeevents.ChatMessageEditedEvent;
import social.network.entities.chat.changeshistory.chatchangeevents.ChatNewMessageSentEvent;
import social.network.entities.chat.messages.SimpleMessage;

public interface CommunicateViaChatDAService {
    void addMessageToChat(Message simpleMessage, int chatId);

    void deleteMessageByIdAndAddEventToChangeHistory(int orderIdMessage, int idChat, ChatMessageDeletedEvent event);

    void updateMessageInChatAndAddEventToChangeHistory(Message simpleMessage, ChatMessageEditedEvent event);

    void addEventToChatChangeHistory(ChatNewMessageSentEvent event, int chatId);

    int userIdWithMessage(int idChat, int orderIdMessage);

    void addMessageToDeletedForChatMember(int idChatMember, int chatId, int orderIdMessage);

    void setLastOrderIdOfMessageWhereStartReadingForChatMember(int idChat, int idSenderRequest);

    void saveMessage(SimpleMessage simpleMessage);
}
