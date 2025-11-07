// src/main/java/vn/iotstar/entity/User.java
package vn.iostar.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Users", schema = "dbo",
        uniqueConstraints = {
                @UniqueConstraint(name="UK_Users_Username", columnNames = "Username"),
                @UniqueConstraint(name="UK_Users_Email", columnNames = "Email")
        })
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserId")
    private Integer userId;

    @Column(name = "Username", length = 50)
    private String username;

    @Column(name = "FullName", length = 100, nullable = false)
    private String fullName;

    @Column(name = "Email", length = 100)
    private String email;

    @Column(name = "Phone", length = 20)
    private String phone;

    @Column(name = "PasswordHash", length = 255, nullable = false)
    private String passwordHash;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RoleId", nullable = false,
            foreignKey = @ForeignKey(name = "FK_Users_Roles"))
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AddressId",
            foreignKey = @ForeignKey(name = "FK_Users_Address"))
    private Address address;

    @OneToMany(mappedBy = "sender")
    private List<Parcel> createdParcels;

    // 'Active' / 'Inactive'
    @Column(name = "Status", length = 20, nullable = false)
    private String status;

    @Column(name = "CreateDate", nullable = false)
    private LocalDateTime createDate;
}
