package social.network.entities.chat.changeshistory.chatchangeevents;

import social.network.entities.chat.changeshistory.ChatChangeEvent;

public class NewChatMemberAddedEvent extends ChatChangeEvent {
    private final int idNewChatMember;

    public NewChatMemberAddedEvent(int idNewChatMember) {
        super(0);
        this.idNewChatMember = idNewChatMember;
    }
}
