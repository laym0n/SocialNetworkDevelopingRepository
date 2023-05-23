package social.network.entities.usersrelationships.friends;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FriendRequest {
    private int idUserSender;
    private int idUserToWhom;
    private OffsetDateTime whenSent;
}
