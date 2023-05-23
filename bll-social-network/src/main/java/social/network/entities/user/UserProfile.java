package social.network.entities.user;

import lombok.Builder;
import social.network.entities.newsfeed.NewsFeed;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserProfile {
    private UserInfo owner;
    private Optional<LocalDate> birthday;
    private NewsFeed profileNewsFeed;
}
