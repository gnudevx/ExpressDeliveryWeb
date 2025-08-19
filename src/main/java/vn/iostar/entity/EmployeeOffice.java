package vn.iostar.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.iostar.embeddedld.RouteHistoryId;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.ColumnDefault;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
@Table(name = "employee_office")
public class EmployeeOffice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Integer id;

	@OneToOne
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	private User user;

	@OneToOne
	@JoinColumn(name = "office_id", referencedColumnName = "office_id")
	private PostOffice office;

	@Column(name = "status", nullable = false)
	@ColumnDefault("'Active'")
	private String status;
}
