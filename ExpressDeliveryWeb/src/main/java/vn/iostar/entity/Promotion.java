// src/main/java/vn/iotstar/entity/Promotion.java
package vn.iostar.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Promotions", schema = "dbo",
        uniqueConstraints = @UniqueConstraint(name="UK_Promotions_Code", columnNames="Code"))
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PromotionId")
    private Integer promotionId;

    @Column(name = "Code", length = 50, nullable = false)
    private String code;

    @Column(name = "Description", length = 200)
    private String description;

    @Column(name = "StartDate", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "EndDate")
    private LocalDateTime endDate;

    @Column(name = "Quantity", nullable = false)
    private Integer quantity;

    @Column(name = "Percentage", nullable = false)
    private Integer percentage; // 0..100

}
