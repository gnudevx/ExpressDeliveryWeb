package vn.iostar.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDetailDTO {
    private int parcelId;
    private int userId;
    private String paymentName;
    private String createDate;
    private LocalDateTime completeDate;
    private int shippingFee;
}
