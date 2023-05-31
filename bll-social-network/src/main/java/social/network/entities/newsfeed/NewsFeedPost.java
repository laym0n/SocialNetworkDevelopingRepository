package social.network.entities.newsfeed;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import social.network.entities.user.UserInfo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsFeedPost {
    private int id;
    private int userId;
    private int orderId;
    private String text;
    private List<byte[]> images;
    private LocalDateTime createdAt;
    private LocalDateTime lastEditTime;
    private List<PostComment> comments;
    private Map<Integer, UserInfo> commentersInfos;
}
