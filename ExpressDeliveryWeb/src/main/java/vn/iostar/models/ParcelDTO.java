package vn.iostar.models;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ParcelDTO {
    private Integer parcelId;
    private String userName;
    private Integer userId;
    private String recipientName;
    private String shipperName;
    private Float weight;
    private String status;
    private String note;
    private String shippingType;
    private LocalDateTime createDate;
    private LocalDateTime completeDate;
    private String paymentMethod;
    private Integer recipientId;
    // Thêm các thuộc tính mới cho Start Office và Destination Office
    private String startOffice; // Tên văn phòng gửi
    private String destinationOffice; // Tên văn phòng nhận
}