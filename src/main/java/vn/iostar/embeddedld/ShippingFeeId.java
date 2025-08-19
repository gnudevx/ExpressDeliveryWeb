package vn.iostar.embeddedld;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
public class ShippingFeeId implements Serializable {
    private Integer shippingTypeId;
    private Integer parcelTypeId;
}
