package vn.iostar.entity;

import jakarta.persistence.*;
import lombok.*;
import vn.iostar.embeddedld.ParcelDetailId;

@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "parcel_details")
public class ParcelDetail {

	@EmbeddedId
	private ParcelDetailId id;

	@OneToOne
	@MapsId("parcelId")
	@JoinColumn(name = "parcel_id", nullable = false)
	private Parcel parcel;

	@ManyToOne
	@MapsId("productId")
	@JoinColumn(name = "product_id", nullable = false)
	private Product product;

	@Column(nullable = false)
	private Integer quantity;

	// Getters and Setters
}
