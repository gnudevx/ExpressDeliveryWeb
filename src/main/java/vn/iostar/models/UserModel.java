package vn.iostar.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.iostar.entity.Role;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {

	private int userId;
	private String address;
	private String email;
	private String fullname;
	private String images;
	private String password;
	private String phone;
	private int roleId;
	private boolean status;
	private Boolean isEdit = false;
	

}