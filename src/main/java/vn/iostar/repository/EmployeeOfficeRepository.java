package vn.iostar.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

//import vn.iostar.entity.EmployeeOffice;

//@Repository
//public interface EmployeeOfficeRepository extends JpaRepository<EmployeeOffice, Integer> {
//
//	@Transactional
//	@Modifying
//	@Query(value = "DELETE FROM employee_office WHERE user_id = :id", nativeQuery = true)
//	void deleteByUserId(@Param("id") Integer id);
//
//	@Query("SELECT eo FROM EmployeeOffice eo WHERE eo.user.userId = :userId AND eo.office.officeId = :officeId")
//	Optional<EmployeeOffice> findByUserIdAndOfficeId(@Param("userId") Integer userId,
//			@Param("officeId") Integer officeId);
//
//	void delete(EmployeeOffice employeeOffice);
//
//	@Query(value = "SELECT TOP 1 * FROM employee_office WHERE user_id = :userId;", nativeQuery = true)
//	EmployeeOffice findByUserId(@Param("userId") Integer userId);
//}
