package social.network.entities.newsfeed;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsFeed {
    private int id;
    private List<NewsFeedPost> posts = new ArrayList<>();
}
