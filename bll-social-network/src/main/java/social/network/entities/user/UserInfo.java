package social.network.entities.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    private int idUser;
    private String userName;
    private String firstName;
    private String secondName;
    private byte[] avatar;
}
