// src/main/java/vn/iotstar/entity/PostOffice.java
package vn.iostar.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "PostOffices", schema = "dbo",
        uniqueConstraints = @UniqueConstraint(name="UK_PostOffices_Code", columnNames="Code"))
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class PostOffice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OfficeId")
    private Integer officeId;

    @Column(name = "Code", length = 20, nullable = false)
    private String code;

    @Column(name = "Name", length = 100, nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AddressId", nullable = false,
            foreignKey = @ForeignKey(name="FK_PostOffices_Address"))
    private Address address;

    @Column(name = "IsActive", nullable = false)
    private Boolean isActive;
}
