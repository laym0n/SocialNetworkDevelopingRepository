package social.network.entities.chat.changeshistory.chatchangeevents;

import social.network.entities.chat.changeshistory.ChatChangeEvent;

public class ChatMessageEditedEvent extends ChatChangeEvent {
    private final int orderIdMessage;

    public ChatMessageEditedEvent(int orderIdMessage) {
        super(0);
        this.orderIdMessage = orderIdMessage;
    }
}
