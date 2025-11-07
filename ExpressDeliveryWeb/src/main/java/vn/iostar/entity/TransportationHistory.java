//// src/main/java/vn/iotstar/entity/TransportationHistory.java
//package vn.iostar.entity;
//
//import jakarta.persistence.*;
//import lombok.*;
//
//import java.time.LocalDateTime;
//
//@Entity
//@Table(name = "TransportationHistory", schema = "dbo",
//        indexes = @Index(name="IX_Track_Parcel_Time", columnList = "ParcelId, CheckInTime"))
//@Data @NoArgsConstructor @AllArgsConstructor @Builder
//public class TransportationHistory {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "HistoryId")
//    private Integer historyId;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "ParcelId", nullable = false,
//            foreignKey = @ForeignKey(name="FK_Track_Parcel"))
//    private Parcel parcel;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "OfficeId",
//            foreignKey = @ForeignKey(name="FK_Track_Office"))
//    private PostOffice office;
//
//    // 'Created','CheckedIn','CheckedOut','OutForDelivery','Delivered','Failed','Returned'
//    @Column(name = "EventType", length = 30, nullable = false)
//    private String eventType;
//
//    @Column(name = "CheckInTime", nullable = false)
//    private LocalDateTime checkInTime;
//
//    @Column(name = "CheckOutTime")
//    private LocalDateTime checkOutTime;
//
//    @Column(name = "Note", length = 500)
//    private String note;
//}
