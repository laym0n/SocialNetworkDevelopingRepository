package social.network.entities.chat.changeshistory.chatchangeevents;

import social.network.entities.chat.changeshistory.ChatChangeEvent;
import social.network.entities.chat.changeshistory.ChatChangeEventType;

public class ChatMessageBlockedEvent extends ChatChangeEvent {
    private int chatId;
    private int orderIdMessage;

    public ChatMessageBlockedEvent(int chatId, int orderIdMessage) {
        super(0, ChatChangeEventType.MESSAGE_BLOCKED);
        this.chatId = chatId;
        this.orderIdMessage = orderIdMessage;
    }
}
