package social.network.jpa.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import social.network.jpa.entities.ids.BlackListUserEntityId;
import social.network.entities.usersrelationships.blacklist.BlackListRelationship;

import java.time.OffsetDateTime;

@Entity
@Data
@Table(name = "black_list_of_users")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BlackListUserEntity {
    @EmbeddedId
    private BlackListUserEntityId id;

    @Column(name = "when_blocked")
    private OffsetDateTime whenBlocked;

    public BlackListUserEntity(BlackListRelationship blackListRelationship) {
        this.id = new BlackListUserEntityId(blackListRelationship);
        this.whenBlocked = blackListRelationship.getWhenAdded();
    }
}
