//
//package vn.iostar.entity;
//import jakarta.persistence.*;
//import lombok.*;
//import vn.iostar.embeddedld.ShippingFeeId;
//
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//@Entity
//@Table(name = "ShippingFees", schema = "dbo")
//@Data @NoArgsConstructor @AllArgsConstructor @Builder
//public class ShippingFee {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//
//    // Loại dịch vụ trực tiếp
//    @Column(length = 20, nullable = false)
//    private String serviceType; // 'Standard', 'Express', 'SameDay'
//
//    // Phí
//    @Column(nullable = false, precision = 18, scale = 2)
//    private BigDecimal fee;
//}
//
