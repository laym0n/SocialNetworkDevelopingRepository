package social.network.dto.requests;

import social.network.dto.modelsdto.messages.BanDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlockMessageRequest {
    private int idOwnerRequest;
    private int orderIdMessage;
    private int idChat;
    private BanDTO banDTO;
}
