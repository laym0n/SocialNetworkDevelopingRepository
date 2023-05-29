package social.network.dto.modelsdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupChatInfoDTO {
    private String chatTitle;
    private byte[] avatar;


}
