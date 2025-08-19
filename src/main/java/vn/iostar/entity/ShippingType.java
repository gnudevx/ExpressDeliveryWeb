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
@Table(name = "shipping_types")
public class ShippingType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer shippingTypeId;

    private String name;

    private String description;

    @OneToMany(mappedBy = "shippingType")
    private List<Parcel> parcels;

    @OneToMany(mappedBy = "shippingType")
    private List<ShippingFee> shippingFees;

    // Getters and Setters
}