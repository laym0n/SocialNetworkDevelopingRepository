package social.network.entities.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import social.network.entities.newsfeed.NewsFeed;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserProfile {
    private PersonalInfo personalInfo;
    private UserInfo owner;
    private Optional<byte[]> avatar;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserProfile that = (UserProfile) o;
        return Objects.equals(getPersonalInfo(), that.getPersonalInfo())
                && Objects.equals(getOwner(), that.getOwner())
                && (Arrays.equals(getAvatar().orElse(null), that.getAvatar().orElse(null)));
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOwner());
    }
}
