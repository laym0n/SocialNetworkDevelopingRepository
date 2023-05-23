package social.network.entities.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    private int idUser;
    private String userName;
    private String firstName;
    private String secondName;
    private Optional<byte[]> avatar;
    private OffsetDateTime lastGetUpdate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserInfo userInfo = (UserInfo) o;
        return getIdUser() == userInfo.getIdUser()
                && getUserName().equals(userInfo.getUserName())
                && getFirstName().equals(userInfo.getFirstName())
                && getSecondName().equals(userInfo.getSecondName())
                && getAvatar().equals(userInfo.getAvatar())
                && getLastGetUpdate().truncatedTo(ChronoUnit.MINUTES)
                    .equals(userInfo.getLastGetUpdate().truncatedTo(ChronoUnit.MINUTES));
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdUser());
    }
}
