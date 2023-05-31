package social.network.entities.chat.changeshistory.chatchangeevents;

import social.network.entities.chat.changeshistory.ChatChangeEvent;

public class ChatMemberBlockedEvent extends ChatChangeEvent {
    private final int idChat;
    private final int idUser;

    public ChatMemberBlockedEvent(int idChat, int idUser) {
        super(0);
        this.idChat = idChat;
        this.idUser = idUser;
    }
}
