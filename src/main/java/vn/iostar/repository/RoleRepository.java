package vn.iostar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import vn.iostar.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{
	@Query(value = "SELECT * FROM roles "
			+ "WHERE name NOT LIKE '%User%' AND name NOT LIKE '%Shipper%';", nativeQuery = true)
	List<Role> findExceptUserShipper();
	
	@Query(value = "SELECT * FROM roles "
			+ "WHERE name NOT LIKE '%User%';", nativeQuery = true)
	List<Role> findExceptUser();
	
	@Query(value = "SELECT TOP 1 * FROM roles WHERE name LIKE CONCAT('%', :name, '%');", nativeQuery = true)
	Role findByName(@Param("name") String name);

}
