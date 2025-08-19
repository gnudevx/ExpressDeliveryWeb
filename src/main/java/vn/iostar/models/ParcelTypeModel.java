package vn.iostar.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParcelTypeModel {

	private int parcelTypeId;
	private String name;
	private Float minWeight;
	private Float maxWeight;
	private Boolean isEdit = false;
	

}
