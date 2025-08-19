package vn.iostar.entity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "payment_methods")
public class PaymentMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer paymentId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Boolean status;

    private String description;

    @OneToMany(mappedBy = "paymentMethod")
    private List<Parcel> parcels;

    // Getters and Setters
}
