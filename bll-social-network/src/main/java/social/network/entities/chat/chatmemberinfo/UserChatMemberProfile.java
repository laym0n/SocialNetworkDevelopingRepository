package social.network.entities.chat.chatmemberinfo;

import lombok.Data;
import social.network.entities.user.UserInfo;

@Data
public class UserChatMemberProfile extends ChatMemberProfile {
    private UserInfo userInfo;
}
