// src/main/java/vn/iotstar/entity/UserOffice.java
package vn.iostar.entity;

import jakarta.persistence.*;
import lombok.*;
import vn.iostar.embeddedld.UserOfficeId;

import java.time.LocalDateTime;

@Entity
@Table(name = "UserOffices", schema = "dbo")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class UserOffice {

    @EmbeddedId
    private UserOfficeId id;

    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UserId",
            foreignKey = @ForeignKey(name="FK_UserOffices_User"))
    private User user;

    @MapsId("officeId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OfficeId",
            foreignKey = @ForeignKey(name="FK_UserOffices_Office"))
    private PostOffice office;

    @Column(name = "IsPrimary", nullable = false)
    private Boolean isPrimary;

    @Column(name = "ToDate")
    private LocalDateTime toDate;
}
