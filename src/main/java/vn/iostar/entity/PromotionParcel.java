//package vn.iostar.entity;
//
//import jakarta.persistence.*;
//import lombok.*;
//import vn.iostar.embeddedld.PromotionParcelId;
//
//import java.time.LocalDateTime;
//
//@Entity
//@Table(name = "Promotion_Parcel", schema = "dbo")
//@Data @NoArgsConstructor @AllArgsConstructor @Builder
//public class PromotionParcel {
//
//    @EmbeddedId
//    private PromotionParcelId id;
//
//    @MapsId("promotionId")
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "PromotionId",
//            foreignKey = @ForeignKey(name="FK_PromoParcel_Promo"))
//    private Promotion promotion;
//
//    @MapsId("parcelId")
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "ParcelId",
//            foreignKey = @ForeignKey(name="FK_PromoParcel_Parcel"))
//    private Parcel parcel;
//
//    @Column(name = "ApplyDate", nullable = false)
//    private LocalDateTime applyDate;
//}