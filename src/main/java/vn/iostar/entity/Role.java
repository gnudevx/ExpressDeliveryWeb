package vn.iostar.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Role {
    @Id
    private Integer roleId;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "role")
    private List<User> users;

    // Getters and Setters
}
