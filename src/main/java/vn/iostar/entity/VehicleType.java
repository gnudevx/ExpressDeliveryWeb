package vn.iostar.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "vehicle_types")
public class VehicleType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer vehicleTypeId;

    private String name;

    @OneToMany(mappedBy = "vehicleType")
    private List<Vehicle> vehicles;

    // Getters and Setters
}
