package social.network.entities.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import social.network.entities.newsfeed.NewsFeed;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserProfile {
    private PersonalInfo personalInfo;
    private UserInfo owner;
    private Optional<byte[]> avatar;
    private NewsFeed profileNewsFeed;
}
