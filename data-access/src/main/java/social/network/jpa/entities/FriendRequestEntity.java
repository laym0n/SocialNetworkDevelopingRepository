package social.network.jpa.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import social.network.jpa.entities.ids.FriendRequestEntityId;
import social.network.entities.usersrelationships.friends.FriendRequest;

import java.time.OffsetDateTime;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "friend_requests")
public class FriendRequestEntity {
    @EmbeddedId
    private FriendRequestEntityId id;

    @Column(name = "when_request_sent")
    private OffsetDateTime whenRequestSent;

    public FriendRequestEntity(FriendRequest friendRequest) {
        this.id = new FriendRequestEntityId(friendRequest);
        this.whenRequestSent = friendRequest.getWhenSent();
    }
    public FriendRequest getFriendRequest() {
        return new FriendRequest(
                id.getUserSenderRequestId(),
                id.getUserToWhomId(),
                whenRequestSent
        );
    }
}
