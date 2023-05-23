package social.network.entities.ids;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import social.network.entities.usersrelationships.blacklist.BlackListRelationship;

import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlackListUserEntityId implements Serializable {
    @Column(name = "id_user_owner")
    private Integer userOwnerId;

    @Column(name = "id_user_blocked")
    private Integer userBlockedId;

    public BlackListUserEntityId(BlackListRelationship blackListRelationship) {
        this.userOwnerId = blackListRelationship.getIdOwnerBlackList();
        this.userBlockedId = blackListRelationship.getIdBlockedUser();
    }
}
