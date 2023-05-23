package social.network.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import social.network.entities.usersrelationships.blacklist.BlackListRelationship;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddUserToBlackListRequest {
    private int idUserSenderRequest;
    private int idUserForAddToBlackList;
    public BlackListRelationship getBlackListRelationship(){
        return BlackListRelationship
                .builder()
                .idBlockedUser(idUserForAddToBlackList)
                .idOwnerBlackList(idUserSenderRequest)
                .whenAdded(OffsetDateTime.now())
                .build();
    }
}
