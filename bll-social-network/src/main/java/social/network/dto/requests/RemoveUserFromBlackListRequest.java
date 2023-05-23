package social.network.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import social.network.entities.usersrelationships.blacklist.BlackListRelationship;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RemoveUserFromBlackListRequest {
    private int idUserSenderRequest;
    private int idUserForRemoveFromBlackList;
    public BlackListRelationship getBlackListRelationship() {
        return BlackListRelationship
                .builder()
                .idOwnerBlackList(idUserSenderRequest)
                .idBlockedUser(idUserForRemoveFromBlackList)
                .build();
    }
}
