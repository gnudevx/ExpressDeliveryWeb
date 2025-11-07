package vn.iostar.model;


import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostOfficeModel {
	
	private Integer officeId;
	@NotEmpty
    private String address;
	@NotEmpty
    private String phone;
    
    private Boolean isEdit = false;
}