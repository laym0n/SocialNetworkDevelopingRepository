package social.network.entities.chat.changeshistory.chatchangeevents;

import social.network.entities.chat.changeshistory.ChatChangeEvent;
import social.network.entities.chat.changeshistory.ChatChangeEventType;
import lombok.Data;

@Data
public class ChatMessageDeletedEvent extends ChatChangeEvent {
    private int orderIdMessage;

    public ChatMessageDeletedEvent(int orderIdMessage) {
        super(0);
        this.orderIdMessage = orderIdMessage;
    }
}
