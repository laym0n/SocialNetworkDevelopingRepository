package social.network.entities.chat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import social.network.entities.socialnetworkuser.SocialNetworkUser;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatMember {
    private long id;
    private int lastOrderIdOfCheckedMessage;
    private int orderIdOfMessageWhereStartReading;
    private SocialNetworkUser socialNetworkUser;
}
