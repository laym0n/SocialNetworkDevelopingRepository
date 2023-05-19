package social.network.entities.user;

import social.network.entities.newsfeed.NewsFeed;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfile {
    private UserInfo owner;
    private OffsetDateTime birthday;
    private NewsFeed profileNewsFeed;
}
