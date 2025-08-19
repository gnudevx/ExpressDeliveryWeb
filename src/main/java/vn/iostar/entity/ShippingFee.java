package vn.iostar.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.iostar.embeddedld.ShippingFeeId;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
@Table(name = "shipping_fee")
public class ShippingFee {

    @EmbeddedId
    private ShippingFeeId id;

    @ManyToOne
    @MapsId("shippingTypeId")
    @JoinColumn(name = "shipping_type_id", nullable = false)
    private ShippingType shippingType;

    @ManyToOne
    @MapsId("parcelTypeId")
    @JoinColumn(name = "parcel_type_id", nullable = false)
    private ParcelType parcelType;

    private Float fee;

    // Getters and Setters
}