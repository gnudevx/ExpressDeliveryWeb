// src/main/java/vn/iotstar/entity/AssigningParcel.java
package vn.iostar.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "ParcelAssignments", schema = "dbo",
        indexes = @Index(name="IX_Parcel_Assign", columnList = "ParcelId"))
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ParcelAssignments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AssignId")
    private Integer assignId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ParcelId", nullable = false,
            foreignKey = @ForeignKey(name="FK_Assignment_Parcel"))
    private Parcel parcel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UserId", nullable = false,
            foreignKey = @ForeignKey(name="FK_Assignment_Shipper"))
    private User shipperID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OfficeId",
            foreignKey = @ForeignKey(name="FK_Assignment_Office"))
    private PostOffice office;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VehicleId",
            foreignKey = @ForeignKey(name="FK_Assign_Vehicle"))
    private Vehicle vehicle;

    // 'Pickup','Transfer','Delivery'
    @Column(name = "AssignmentType", length = 20, nullable = false)
    private String assignmentType;

    @Column(name = "ShippingDate")
    private LocalDateTime shippingDate;

    @Column(name = "CompleteDate")
    private LocalDateTime completeDate;

    @Column(name = "IsActive", nullable = false)
    private Boolean isActive;
}
