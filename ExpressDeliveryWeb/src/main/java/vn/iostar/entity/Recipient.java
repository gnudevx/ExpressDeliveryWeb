// src/main/java/vn/iotstar/entity/Recipient.java
package vn.iostar.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Recipients", schema = "dbo")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Recipient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RecipientId")
    private Integer recipientId;

    @Column(name = "FullName", length = 100, nullable = false)
    private String fullName;

    @Column(name = "Phone", length = 20, nullable = false)
    private String phone;

    @Column(name = "Email", length = 100)
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AddressId", nullable = false,
            foreignKey = @ForeignKey(name="FK_Recipients_Address"))
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CreatedByUserId",
            foreignKey = @ForeignKey(name="FK_Recipients_Creator"), nullable = true)
    private User createdBy;
}
