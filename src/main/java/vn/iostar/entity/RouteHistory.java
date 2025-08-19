package vn.iostar.entity;

import jakarta.persistence.*;
import lombok.*;
import vn.iostar.embeddedld.RouteHistoryId;

import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "route_history")
public class RouteHistory {

    @EmbeddedId
    private RouteHistoryId id;

    @ManyToOne
    @MapsId("parcelId")
    @JoinColumn(name = "parcel_id", nullable = false)
    private Parcel parcel;

    @ManyToOne
    @MapsId("officeId")
    @JoinColumn(name = "office_id", nullable = false)
    private PostOffice postOffice;

    @Column(name = "checkin_time", nullable = false)
    private LocalDateTime checkinTime;

    @Column(name = "checkout_time")
    private LocalDateTime checkoutTime;

    private String note;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name = "driver_id", nullable = false)
    private User driver;

    // Getters and Setters
}
