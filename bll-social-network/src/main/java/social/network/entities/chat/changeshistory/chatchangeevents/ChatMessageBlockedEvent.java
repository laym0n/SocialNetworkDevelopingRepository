package social.network.entities.chat.changeshistory.chatchangeevents;

import social.network.entities.chat.changeshistory.ChatChangeEvent;

public class ChatMessageBlockedEvent extends ChatChangeEvent {
    private final int chatId;
    private final int orderIdMessage;

    public ChatMessageBlockedEvent(int chatId, int orderIdMessage) {
        super(0);
        this.chatId = chatId;
        this.orderIdMessage = orderIdMessage;
    }
}
