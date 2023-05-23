package social.network.entities.usersrelationships.blacklist;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BlackListRelationship {
    private int idOwnerBlackList;
    private int idBlockedUser;
    private OffsetDateTime whenAdded;
}
