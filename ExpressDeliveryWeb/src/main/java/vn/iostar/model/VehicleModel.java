package vn.iostar.model;


import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.iostar.entity.VehicleType;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleModel {
	
	private Integer vehicleId;
	@NotEmpty
	private String licensePlate;
	
	private VehicleType vehicleType;
	
	private String status;
    
    private Boolean isEdit = false;
}