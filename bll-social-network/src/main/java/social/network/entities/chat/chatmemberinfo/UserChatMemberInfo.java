package social.network.entities.chat.chatmemberinfo;

import social.network.entities.user.UserInfo;
import lombok.Data;

@Data
public class UserChatMemberInfo extends ChatMemberInfo {
    private UserInfo userInfo;
}
