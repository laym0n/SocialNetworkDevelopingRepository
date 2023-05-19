package social.network.entities.chat.changeshistory.chatchangeevents;

import social.network.entities.chat.changeshistory.ChatChangeEvent;
import social.network.entities.chat.changeshistory.ChatChangeEventType;

public class ChatDeletedEvent extends ChatChangeEvent {
    public ChatDeletedEvent() {
        super(0, ChatChangeEventType.CHAT_DELETED);
    }
}
