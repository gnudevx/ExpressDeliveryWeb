// src/main/java/vn/iotstar/entity/Vehicle.java
package vn.iostar.entity;

import jakarta.persistence.*;
import lombok.*;
import vn.iostar.entity.VehicleType;

@Entity
@Table(name = "Vehicles", schema = "dbo",
        uniqueConstraints = @UniqueConstraint(name="UK_Vehicles_LicensePlate", columnNames="LicensePlate"))
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VehicleId")
    private Integer vehicleId;

    @Column(name = "LicensePlate", length = 20, nullable = false)
    private String licensePlate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VehicleTypeId", nullable = false,
            foreignKey = @ForeignKey(name="FK_Vehicles_Type"))
    private VehicleType vehicleType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UserId",
            foreignKey = @ForeignKey(name="FK_Vehicles_User"))
    private User user; // chủ sở hữu (nếu có)

    // 'Active' / 'Maintenance' / 'Inactive'
    @Column(name = "Status", length = 20, nullable = false)
    private String status;
}
