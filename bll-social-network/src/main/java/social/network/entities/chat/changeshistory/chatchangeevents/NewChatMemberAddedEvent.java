package social.network.entities.chat.changeshistory.chatchangeevents;

import social.network.entities.chat.changeshistory.ChatChangeEvent;
import social.network.entities.chat.changeshistory.ChatChangeEventType;

public class NewChatMemberAddedEvent extends ChatChangeEvent {
    private int idNewChatMember;

    public NewChatMemberAddedEvent(int idNewChatMember) {
        super(0);
        this.idNewChatMember = idNewChatMember;
    }
}
