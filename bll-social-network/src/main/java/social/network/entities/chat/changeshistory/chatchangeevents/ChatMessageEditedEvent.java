package social.network.entities.chat.changeshistory.chatchangeevents;

import social.network.entities.chat.changeshistory.ChatChangeEvent;
import social.network.entities.chat.changeshistory.ChatChangeEventType;

public class ChatMessageEditedEvent extends ChatChangeEvent {
    private int orderIdMessage;

    public ChatMessageEditedEvent(int orderIdMessage) {
        super(0);
        this.orderIdMessage = orderIdMessage;
    }
}
