package social.network.dto.requests;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import social.network.dto.modelsdto.PrivateChatInfoDTO;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditPrivateChatRequest {
    private int idUserSender;
    private int chatId;
    private PrivateChatInfoDTO newInfo;
}
