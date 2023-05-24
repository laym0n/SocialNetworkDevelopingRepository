package social.network.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import social.network.entities.user.UserProfile;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckUserProfileResponse {
    private int idUser;
    private String userName;
    private String firstName;
    private String secondName;
    private byte[] avatar;
    private LocalDate birthday;
    private boolean isFriend;
    private boolean isCurrentUserInBlackList;
    private boolean isOwnerProfileInBlackList;
//    private boolean
    public CheckUserProfileResponse(UserProfile userProfile) {
        this.idUser = userProfile.getOwner().getIdUser();
        this.userName = userProfile.getOwner().getUserName();
//        this.firstName = userProfile.getOwner().getFirstName();
//        this.secondName = userProfile.getOwner().getSecondName();
//        this.avatar = userProfile.getOwner().getAvatar();
//        this.birthday = userProfile.getBirthday();
    }
}
