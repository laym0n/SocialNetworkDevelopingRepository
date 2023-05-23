package social.network.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import social.network.dto.modelsdto.UserInfoDTO;
import social.network.entities.user.UserInfo;
import social.network.entities.user.UserProfile;
import lombok.Data;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.Optional;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserProfileRequest {
    private int idOwnerRequest;
    private String firstName;
    private String secondName;
    private LocalDate newBirthday;

    public UserProfile getUserProfile() {
        return new UserProfile(
                UserInfo.builder()
                        .firstName(firstName)
                        .secondName(secondName)
                        .build(),
                Optional.of(newBirthday),
                null
        );
    }
}
