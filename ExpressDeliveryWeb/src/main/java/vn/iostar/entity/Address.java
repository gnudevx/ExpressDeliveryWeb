// src/main/java/vn/iotstar/entity/Address.java
package vn.iostar.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Address", schema = "dbo")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AddressId")
    private Integer addressId;

    @Column(name = "City", length = 100, nullable = false)
    private String city;

    @Column(name = "District", length = 100, nullable = false)
    private String district;

    @Column(name = "Ward", length = 100, nullable = false)
    private String ward;

    @Column(name = "Detail", length = 200, nullable = false)
    private String detail;
}
