package social.network.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import social.network.entities.usersrelationships.friends.FriendRequest;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AcceptFriendRequestRequest {
    private int idUserSenderRequest;
    private int idUserSenderFriendRequest;
    public FriendRequest getFriendRequest(){
        return FriendRequest.builder()
                .idUserToWhom(idUserSenderRequest)
                .idUserSender(idUserSenderFriendRequest)
                .build();
    }
}
