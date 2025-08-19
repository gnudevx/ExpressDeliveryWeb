package vn.iostar.models;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShippingFeeModel {

    private Integer shippingTypeId;
    private Integer parcelTypeId;

    @NotNull(message = "Shipping fee is required")
    @Positive(message = "Shipping fee must be greater than 0")
    private Double fee;

    private boolean isEdit;
}
