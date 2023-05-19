package social.network.entities.chat.changeshistory.chatchangeevents;

import social.network.entities.chat.changeshistory.ChatChangeEvent;
import social.network.entities.chat.changeshistory.ChatChangeEventType;

public class ChatInfoChangedEvent extends ChatChangeEvent {

    public ChatInfoChangedEvent() {
        super(0, ChatChangeEventType.CHAT_INFO_CHANGED);
    }
}
