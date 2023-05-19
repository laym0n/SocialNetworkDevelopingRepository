package social.network.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "group_chat_members")
@AllArgsConstructor
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "id")
public class GroupChatMemberEntity extends ChatMemberEntity{
    @Column(name = "chat_member_name", nullable = false)
    private String name;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "group_chat_members_roles",
            joinColumns = @JoinColumn(name = "id_member"),
            inverseJoinColumns = @JoinColumn(name = "id_group_member_role"))
    private List<GroupChatRoleEntity> roles = new ArrayList<>();
}
