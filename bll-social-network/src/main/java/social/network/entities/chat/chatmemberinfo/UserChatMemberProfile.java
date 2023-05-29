package social.network.entities.chat.chatmemberinfo;

import social.network.entities.user.UserInfo;
import lombok.Data;

@Data
public class UserChatMemberProfile extends ChatMemberProfile {
    private UserInfo userInfo;
}
