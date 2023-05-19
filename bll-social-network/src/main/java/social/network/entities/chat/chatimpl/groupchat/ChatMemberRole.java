package social.network.entities.chat.chatimpl.groupchat;

import lombok.Getter;

@Getter
public enum ChatMemberRole {
    SIMPLE_MEMBER(0, 20),
    CHAT_ADMIN(0, 50),
    OWNER(0, 100);
    int idGroup;
    int power;

    ChatMemberRole(int idGroup, int power) {
        this.idGroup = idGroup;
        this.power = power;
    }
}
