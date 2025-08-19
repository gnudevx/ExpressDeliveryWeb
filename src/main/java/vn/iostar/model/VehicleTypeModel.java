package vn.iostar.model;


import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleTypeModel {
	
	private Integer vehicleTypeId;
	@NotEmpty
    private String name;
    
    private Boolean isEdit = false;
}