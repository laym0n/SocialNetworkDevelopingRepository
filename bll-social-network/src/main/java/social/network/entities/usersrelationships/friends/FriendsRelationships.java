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
public class FriendsRelationships {
    private int firstIdUser;
    private int secondIdUser;
    private OffsetDateTime whenAccepted;
}
