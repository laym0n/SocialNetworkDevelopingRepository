package social.network.jpa.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import social.network.jpa.entities.ids.FriendEntityId;
import social.network.entities.usersrelationships.friends.FriendRequest;

import java.time.OffsetDateTime;

@Entity
@Data
@Table(name = "friends")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FriendEntity {
    @EmbeddedId
    private FriendEntityId id;

    @Column(name = "when_became_friends")
    private OffsetDateTime whenBecameFriends;

    public FriendEntity(FriendRequest friendRequest) {
        this.id = new FriendEntityId(friendRequest);
        this.whenBecameFriends = OffsetDateTime.now();
    }
}
