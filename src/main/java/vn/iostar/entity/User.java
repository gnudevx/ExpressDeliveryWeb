package vn.iostar.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

import javax.print.attribute.standard.DateTimeAtCompleted;

import org.springframework.web.multipart.MultipartFile;

import javax.print.attribute.standard.DateTimeAtCompleted;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    private String username;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    private String rePassword;

    @Column(nullable = false)
    private String fullName;

    private String phone;

    @Transient
    private MultipartFile images;

    private String imagePath;

    
    @JoinColumn(name = "create_date")
    private LocalDateTime createDate;

 
    
    @Column(columnDefinition = "NVARCHAR(500)")
    private String address;
    
    @Column(nullable = false)
    private int status;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = true)
    private Role role;
    @OneToMany(mappedBy = "user")
    private List<Parcel> createdParcels;

    @OneToMany(mappedBy = "shipper")
    private List<Parcel> shippedParcels;

    @OneToMany(mappedBy = "driver")
    private List<RouteHistory> routeHistories;
    
    @Transient
    public String getAvatarImagePath(){
        if(imagePath == null || userId == null){
            return null;
        }
        return "/update-avatar/" + userId +"/"+ imagePath;
    }

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private EmployeeOffice employOffice;
}