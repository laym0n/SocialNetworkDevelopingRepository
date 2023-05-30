package social.network.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import social.network.entities.socialnetworkuser.SocialNetworkUser;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FriendDTO {
    private SocialNetworkUser socialNetworkUser;
    private int friendId;
}
