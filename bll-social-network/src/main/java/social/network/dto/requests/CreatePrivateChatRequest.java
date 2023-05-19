package social.network.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import social.network.dto.modelsdto.PrivateChatInfoDTO;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreatePrivateChatRequest {
    private int idUserSenderRequest;
    private PrivateChatInfoDTO privateChatInfoDTO;
}
