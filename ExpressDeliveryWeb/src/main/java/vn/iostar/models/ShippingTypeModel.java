package vn.iostar.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShippingTypeModel {

	private int shippingTypeId;
	private String name;
	private String description;
	private Boolean isEdit = false;
	

}
