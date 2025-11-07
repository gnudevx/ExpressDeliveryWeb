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

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {
	
	private Integer userId;
	@NotEmpty
    private String email;
	@NotEmpty
    private String password;
	@NotEmpty
    private String fullname;
	@NotEmpty
    private String phone;

    private String images;
    
    private LocalDateTime createDate;
    
    private Role role;

    @NotEmpty
    private String address;
    
    private Boolean status;
    
    private Boolean isEdit = false;
}
