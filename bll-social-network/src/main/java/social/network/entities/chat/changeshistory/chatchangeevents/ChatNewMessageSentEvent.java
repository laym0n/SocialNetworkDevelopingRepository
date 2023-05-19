package social.network.entities.chat.changeshistory.chatchangeevents;

import social.network.entities.chat.changeshistory.ChatChangeEvent;
import social.network.entities.chat.changeshistory.ChatChangeEventType;

public class ChatNewMessageSentEvent extends ChatChangeEvent {

    public ChatNewMessageSentEvent() {
        super(0, ChatChangeEventType.NEW_MESSAGE_WAS_SENT);
    }
}
