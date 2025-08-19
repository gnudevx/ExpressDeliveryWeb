package vn.iostar.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleDTO {
    private Integer userId;
    private String email;
    private String password;
    private String fullname;
    private String phone;
    private String name;
    private String status; //status của employee_office
    private Integer officeId;
}
