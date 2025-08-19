package vn.iostar.entity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "parcels")
public class Parcel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer parcelId;
    // mã vận đơn: -> know user id:
    @Column(name = "ladingCode", unique = true, nullable = false)
    private String ladingCode;

    @PrePersist
    private void generateLadingCode() {
        this.ladingCode = "LDC-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "recipient_id")
    private Recipient recipient;

    @ManyToOne
    @JoinColumn(name = "payment_id")
    private PaymentMethod paymentMethod;

    @ManyToOne
    @JoinColumn(name = "start_office_id")
    private PostOffice startOffice;

    @ManyToOne
    @JoinColumn(name = "destination_office_id")
    private PostOffice destinationOffice;

    @ManyToOne
    @JoinColumn(name = "shipper_id", nullable = true)
    private User shipper;

    private Float weight;
    
    private String image;

    private String status;

    private String note;
    
    //Thêm chi phí vận chuyển
    @JoinColumn(name = "shipping_fee")
    private int shippingFee;
    
    @ManyToOne
    @JoinColumn(name = "shipping_type_id")
    private ShippingType shippingType;
    
    @ManyToOne
    @JoinColumn(name = "parcel_type_id", nullable = false)
    private ParcelType parcelType;


    private LocalDateTime createDate;

    private LocalDateTime completeDate;

    @OneToMany(mappedBy = "parcel")
    private List<ParcelDetail> parcelDetails;

    @OneToMany(mappedBy = "parcel")
    private List<RouteHistory> routeHistories;

    // Getters and Setters
}