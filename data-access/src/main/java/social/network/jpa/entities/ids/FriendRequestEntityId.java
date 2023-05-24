package social.network.jpa.entities.ids;

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
public class FriendRequestEntityId implements Serializable {
    @Column(name = "id_user_sender_request")
    private Integer userSenderRequestId;

    @Column(name = "id_user_to_whom")
    private Integer userToWhomId;

    public FriendRequestEntityId(FriendRequest friendRequest) {
        this.userSenderRequestId = friendRequest.getIdUserSender();
        this.userToWhomId = friendRequest.getIdUserToWhom();
    }
}
