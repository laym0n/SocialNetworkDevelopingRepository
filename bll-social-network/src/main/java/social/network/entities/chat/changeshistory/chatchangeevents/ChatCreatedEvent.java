package social.network.entities.chat.changeshistory.chatchangeevents;

import social.network.entities.chat.changeshistory.ChatChangeEvent;
import social.network.entities.chat.changeshistory.ChatChangeEventType;

public class ChatCreatedEvent extends ChatChangeEvent {

    public ChatCreatedEvent() {
        super(0, ChatChangeEventType.CHAT_CREATED);
    }
}
