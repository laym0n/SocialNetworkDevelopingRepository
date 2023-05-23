package social.network.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import social.network.entities.usersrelationships.friends.FriendRequest;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SendNewFriendRequestRequest {
    private int idUserSenderRequest;
    private int idUserForWhomRequest;
    public FriendRequest getFriendRequest(){
        return FriendRequest.builder()
                .idUserSender(idUserSenderRequest)
                .idUserToWhom(idUserForWhomRequest)
                .whenSent(OffsetDateTime.now())
                .build();
    }
}
