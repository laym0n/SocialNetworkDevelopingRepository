package social.network.entities.usersrelationships.friends;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class FriendRequest {
    private int idUserSender;
    private int idUserToWhom;
    private OffsetDateTime whenSent;
}
