package social.network.entities.chat.changeshistory.chatchangeevents;

import social.network.entities.chat.changeshistory.ChatChangeEvent;
import social.network.entities.chat.changeshistory.ChatChangeEventType;

public class ChatMemberBlockedEvent extends ChatChangeEvent {
    private int idChat;
    private int idUser;

    public ChatMemberBlockedEvent(int idChat, int idUser) {
        super(0);
        this.idChat = idChat;
        this.idUser = idUser;
    }
}
