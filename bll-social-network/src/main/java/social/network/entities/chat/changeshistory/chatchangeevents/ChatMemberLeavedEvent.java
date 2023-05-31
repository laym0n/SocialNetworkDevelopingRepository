package social.network.entities.chat.changeshistory.chatchangeevents;

import social.network.entities.chat.changeshistory.ChatChangeEvent;

public class ChatMemberLeavedEvent extends ChatChangeEvent {
    private final int idChatMember;

    public ChatMemberLeavedEvent(int idChatMember) {
        super(0);
        this.idChatMember = idChatMember;
    }
}
