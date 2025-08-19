package vn.iostar.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
@Table(name = "parcel_types")
public class ParcelType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer parcelTypeId;

    private String name;

    @Column(nullable = false)
    private Float minWeight;

    @Column(nullable = false)
    private Float maxWeight;

    @OneToMany(mappedBy = "parcelType")
    private List<ShippingFee> shippingFees;

    // Getters and Setters
}