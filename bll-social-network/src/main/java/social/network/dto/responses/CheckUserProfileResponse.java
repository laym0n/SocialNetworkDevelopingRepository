package social.network.dto.responses;

import lombok.Data;
import social.network.entities.user.UserProfile;

import java.time.OffsetDateTime;

@Data
public class CheckUserProfileResponse {
    private int idUser;
    private String username;
    private String firstName;
    private String secondName;
    private byte[] avatar;
    private OffsetDateTime birthday;
    public CheckUserProfileResponse(UserProfile userProfile) {
        this.idUser = userProfile.getOwner().getIdUser();
        this.username = userProfile.getOwner().getUserName();
        this.firstName = userProfile.getOwner().getFirstName();
        this.secondName = userProfile.getOwner().getSecondName();
        this.avatar = userProfile.getOwner().getAvatar();
        this.birthday = userProfile.getBirthday();
    }
}
