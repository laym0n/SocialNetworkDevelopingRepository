package social.network.dto.requests;

import lombok.Data;

@Data
public class ClearMessageHistoryRequest {
    private int idSenderRequest;
    private int idChat;
}
