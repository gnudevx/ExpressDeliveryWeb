package vn.iostar.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentMethodModel {
	
	private Integer paymentId;
	@NotEmpty
    private String name;
	
    private Boolean status;

    private String description;
    
    private Boolean isEdit = false;
}