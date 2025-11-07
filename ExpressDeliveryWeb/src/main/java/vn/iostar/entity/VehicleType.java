
package vn.iostar.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "VehicleTypes", schema = "dbo",
        uniqueConstraints = @UniqueConstraint(name="UK_VehicleTypes_Name", columnNames="Name"))
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class VehicleType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VehicleTypeId")
    private Integer VehicleTypeId;

    @Column(name = "Name", length = 50, nullable = false)
    private String name;
}
