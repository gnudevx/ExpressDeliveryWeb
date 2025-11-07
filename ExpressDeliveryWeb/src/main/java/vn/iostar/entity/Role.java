// src/main/java/vn/iotstar/entity/Role.java
package vn.iostar.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Roles", schema = "dbo", uniqueConstraints = {
        @UniqueConstraint(name = "UK_Role_RoleName", columnNames = "RoleName")
})
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RoleId")
    private Integer roleId;

    @Column(name = "RoleName", length = 50, nullable = false)
    private String roleName;
}
