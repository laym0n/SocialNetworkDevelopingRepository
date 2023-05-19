package social.network.entities.chat.changeshistory.chatchangeevents;

import social.network.entities.chat.changeshistory.ChatChangeEvent;
import social.network.entities.chat.changeshistory.ChatChangeEventType;

public class NewChatMemberAddedEvent extends ChatChangeEvent {
    private int idNewChatMember;

    public NewChatMemberAddedEvent(int idNewChatMember) {
        super(0, ChatChangeEventType.NEW_CHAT_MEMBER_ADDED);
        this.idNewChatMember = idNewChatMember;
    }
}
