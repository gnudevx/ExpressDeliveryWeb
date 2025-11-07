// src/main/java/vn/iostar/entity/Parcel.java
package vn.iostar.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "Parcels", schema = "dbo",
        indexes = @Index(name="IX_Parcels_TrackingCode", columnList = "TrackingCode", unique = true))
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Parcel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ParcelId")
    private Integer parcelId;

    @Column(name = "TrackingCode", length = 50, nullable = false)
    private String trackingCode;

    // 'Standard' / 'Express' / 'SameDay'
    @Column(name = "ServiceType", length = 20, nullable = false)
    private String serviceType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UserId", nullable = false,
            foreignKey = @ForeignKey(name="FK_Parcels_User"))
    private User sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RecipientId", nullable = false,
            foreignKey = @ForeignKey(name="FK_Parcels_Recipient"))
    private Recipient recipient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OriginOfficeId", nullable = false,
            foreignKey = @ForeignKey(name="FK_Parcels_Origin"))
    private PostOffice originOffice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DestinationOfficeId", nullable = false,
            foreignKey = @ForeignKey(name="FK_Parcels_Destination"))
    private PostOffice destinationOffice;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "promotionId", nullable = true,
            foreignKey = @ForeignKey(name = "FK_Parcels_Promotion"))
    private Promotion promotion;

    @Column(name = "CreateDate", nullable = false)
    private LocalDateTime createDate;

    @Column(name = "CompleteDate")
    private LocalDateTime completeDate;

    @Column(name = "LengthCm", precision = 10, scale = 2)
    private BigDecimal lengthCm;

    @Column(name = "WidthCm", precision = 10, scale = 2)
    private BigDecimal widthCm;

    @Column(name = "HeightCm", precision = 10, scale = 2)
    private BigDecimal heightCm;

    @Column(name = "WeightKg", precision = 10, scale = 3)
    private BigDecimal weightKg;

    @Column(name = "DeclaredValue", precision = 18, scale = 2)
    private BigDecimal declaredValue;

    @Column(name = "CODAmount", precision = 18, scale = 2)
    private BigDecimal codAmount;

    @Column(name = "ShippingFee", precision = 18, scale = 2, nullable = false)
    private BigDecimal shippingFee;

    @Column(name = "Discount", precision = 18, scale = 2, nullable = false)
    private BigDecimal discount;

    @Column(name = "TotalCost", precision = 18, scale = 2, insertable = false, updatable = false)
    private BigDecimal totalCost; // computed column

    // 'Created','AtOrigin','InTransit','ArrivedHub','OutForDelivery','Delivered','Failed','Returned'
    @Column(name = "Status", length = 20, nullable = false)
    private String status;

    @Column(name = "LastUpdate", nullable = false)
    private LocalDateTime lastUpdate;

    @Column(name = "Notes", length = 500)
    private String notes;
}
