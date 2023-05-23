package social.network.entities.ids;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import social.network.entities.usersrelationships.friends.FriendRequest;

import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FriendEntityId implements Serializable {
    @Column(name = "first_user_id")
    private Integer firstUserId;

    @Column(name = "second_user_id")
    private Integer secondUserId;

    public FriendEntityId(FriendRequest friendRequest) {
        this.firstUserId = friendRequest.getIdUserToWhom();
        this.secondUserId = friendRequest.getIdUserSender();
    }
}
