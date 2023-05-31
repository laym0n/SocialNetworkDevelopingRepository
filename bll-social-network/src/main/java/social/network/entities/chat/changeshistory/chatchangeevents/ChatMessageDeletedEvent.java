package social.network.entities.chat.changeshistory.chatchangeevents;

import lombok.Data;
import social.network.entities.chat.changeshistory.ChatChangeEvent;

@Data
public class ChatMessageDeletedEvent extends ChatChangeEvent {
    private int orderIdMessage;

    public ChatMessageDeletedEvent(int orderIdMessage) {
        super(0);
        this.orderIdMessage = orderIdMessage;
    }
}
