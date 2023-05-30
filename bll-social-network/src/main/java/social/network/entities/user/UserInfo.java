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
    private boolean isBlocked;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserInfo userInfo = (UserInfo) o;
        return getIdUser() == userInfo.getIdUser()
                && getUserName().equals(userInfo.getUserName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdUser());
    }
}
