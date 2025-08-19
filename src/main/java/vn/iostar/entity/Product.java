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
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;

    private String name;

    private String description;

    private String image;

    private Float weight;

    private Float money;
    @OneToMany(mappedBy = "product")
    private List<ParcelDetail> parcelDetails;
    
    // Getters and Setters
}
