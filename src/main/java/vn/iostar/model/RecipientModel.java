package vn.iostar.model;


import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.iostar.entity.Role;
import vn.iostar.entity.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipientModel {
	
	private Integer recipientId;
	@NotEmpty
    private String fullname;
    @NotEmpty
    private String phone;
    @NotEmpty
    private String address;
    
    private User user;
}
