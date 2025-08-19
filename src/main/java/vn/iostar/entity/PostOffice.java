package vn.iostar.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "post_offices")
public class PostOffice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "office_id")
    private Integer officeId;

    private String address;

    private String phone;

    @OneToMany(mappedBy = "startOffice")
    private List<Parcel> parcelsAsStart;

    @OneToMany(mappedBy = "destinationOffice")
    private List<Parcel> parcelsAsDestination;

    @OneToMany(mappedBy = "postOffice")
    private List<RouteHistory> routeHistories;
    
    @OneToOne(mappedBy = "office", cascade = CascadeType.ALL)
    private EmployeeOffice eo;
}
