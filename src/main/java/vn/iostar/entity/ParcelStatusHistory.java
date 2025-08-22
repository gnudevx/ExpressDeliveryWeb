package vn.iostar.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "ParcelStatusHistory")
public class ParcelStatusHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer statusHistoryId;

    @ManyToOne
    @JoinColumn(name = "ParcelId", nullable = false)
    private Parcel parcel;

    @Column(nullable = false, length = 20)
    private String status;

    @Column(nullable = false)
    private LocalDateTime updateDate = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "UpdatedByUserId")
    private User updatedBy;

    private String note;
}
