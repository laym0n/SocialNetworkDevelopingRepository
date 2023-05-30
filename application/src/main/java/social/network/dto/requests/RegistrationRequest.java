package social.network.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;
import social.network.dto.modelsdto.UserInfoDTO;
import social.network.entities.user.PersonalInfo;
import social.network.entities.user.UserInfo;
import social.network.entities.user.UserProfile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

@Data
@Validated
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequest {
    @NotBlank(message = "Username is required")
    @Pattern(regexp = "\\S+", message = "Username cannot contain only whitespace")
    private String userName = "";
    @NotBlank(message = "Password is required")
    @Pattern(regexp = "\\S+", message = "Username cannot contain only whitespace")
    private String password = "";
    @NotBlank(message = "First Name is required")
    @Pattern(regexp = "\\S+", message = "Username cannot contain only whitespace")
    private String firstName = "";
    @NotBlank(message = "Second Name is required")
    @Pattern(regexp = "\\S+", message = "Username cannot contain only whitespace")
    private String secondName = "";
    private LocalDate birthday;
    private MultipartFile avatar;
    public SignUpRequest getSignUpRequest() throws IOException {
        return SignUpRequest.builder()
                .userName(userName)
                .password(password)
                .userProfile(
                        UserProfile
                                .builder()
                                .personalInfo(
                                        PersonalInfo
                                                .builder()
                                                .birthday(Optional.ofNullable(birthday))
                                                .secondName(secondName)
                                                .firstName(firstName)
                                                .build()
                                )
                                .avatar(Optional.ofNullable(avatar == null ? null : avatar.getBytes()))
                                .owner(
                                        new UserInfo(0, userName, false)
                                )
                                .build()
                )

                .build();
    }

}
