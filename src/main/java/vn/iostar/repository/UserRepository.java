package vn.iostar.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import vn.iostar.entity.PostOffice;
import vn.iostar.entity.User;

import java.util.List;
import java.util.Map;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	@Query(value = "SELECT count(*) AS number FROM users "
			+ "WHERE CONVERT(DATE, create_date) = CONVERT(DATE, GETDATE()) "
			+ "AND role_id = "
			+ "(SELECT TOP 1 role_id from roles where name LIKE '%User%')", nativeQuery = true)
    long countNewCustomerDay();
	
	@Query(value = "SELECT count(*) AS number "
			+ "FROM users "
			+ "WHERE MONTH(create_date) = MONTH(GETDATE()) "
			+ "AND YEAR(create_date) = YEAR(GETDATE()) "
			+ "AND role_id = (SELECT TOP 1 role_id from roles where name LIKE '%User%')", nativeQuery = true)
	long countNewCustomerMonth();
	
	@Query(value = "SELECT count(*) AS number "
			+ "FROM users "
			+ "WHERE YEAR(create_date) = YEAR(GETDATE()) "
			+ "AND role_id = (SELECT TOP 1 role_id from roles where name LIKE '%User%')", nativeQuery = true)
	long countNewCustomerYear();
	
	@Query(value = "SELECT count(*) AS number "
			+ "FROM users "
			+ "WHERE role_id = (SELECT TOP 1 role_id from roles where name LIKE '%User%')", nativeQuery = true)
	long countTotalCustomer();
	
	@Query(value = "WITH AllMonths AS (\r\n"
			+ "SELECT 1 AS month_number UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 \r\n"
			+ "UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 \r\n"
			+ "UNION SELECT 9 UNION SELECT 10 UNION SELECT 11 UNION SELECT 12),\r\n"
			+ "Customers AS (SELECT * FROM users WHERE role_id = \r\n"
			+ "(SELECT TOP 1 role_id FROM roles WHERE name LIKE '%User%'))\r\n"
			+ "SELECT COUNT(p.user_id) AS totalMonth \r\n"
			+ "FROM AllMonths m LEFT JOIN Customers p  \r\n"
			+ "ON MONTH(p.create_date) = m.month_number \r\n"
			+ "AND YEAR(p.create_date) = YEAR(GETDATE()) \r\n"
			+ "GROUP BY m.month_number\r\n"
			+ "ORDER BY m.month_number;", nativeQuery = true)
	List<Long> getMonthlyTotal();
	
	@Query(value = "WITH Quarters AS  \r\n"
			+ "(SELECT 1 AS quarter_number UNION  \r\n"
			+ "SELECT 2 UNION  \r\n"
			+ "SELECT 3 UNION  \r\n"
			+ "SELECT 4),\r\n"
			+ "Customers AS (SELECT * FROM users WHERE role_id = \r\n"
			+ "(SELECT TOP 1 role_id FROM roles WHERE name LIKE '%User%'))\r\n"
			+ "SELECT COUNT(p.user_id) AS totalQuarter  \r\n"
			+ "FROM Quarters q  \r\n"
			+ "LEFT JOIN Customers p ON q.quarter_number = CASE   \r\n"
			+ "WHEN MONTH(p.create_date) BETWEEN 1 AND 3 THEN 1  \r\n"
			+ "WHEN MONTH(p.create_date) BETWEEN 4 AND 6 THEN 2  \r\n"
			+ "WHEN MONTH(p.create_date) BETWEEN 7 AND 9 THEN 3  \r\n"
			+ "WHEN MONTH(p.create_date) BETWEEN 10 AND 12 THEN 4 END  \r\n"
			+ "AND YEAR(p.create_date) = YEAR(GETDATE())  \r\n"
			+ "GROUP BY q.quarter_number  \r\n"
			+ "ORDER BY q.quarter_number;", nativeQuery = true)
	List<Long> getQuarterlyTotal();
	
	@Query(value = "SELECT * FROM users WHERE role_id = \r\n"
			+ "(SELECT TOP 1 role_id FROM roles WHERE name LIKE '%User%')"
			+ "ORDER BY create_date DESC", nativeQuery = true)
	List<User> findCustomer();
	
	@Query(value = "WITH TableUser AS (\r\n"
			+ "SELECT * FROM users),\r\n"
			+ "TableRole AS (\r\n"
			+ "SELECT * FROM roles\r\n"
			+ "WHERE name NOT LIKE '%user%'),\r\n"
			+ "Office AS (\r\n"
			+ "SELECT * FROM employee_office)\r\n"
			+ "SELECT u.user_id, u.email, u.password, u.fullName, \r\n"
			+ "u.phone, u.create_date, r.name, o.status, o.office_id\r\n"
			+ "FROM TableUser u\r\n"
			+ "JOIN TableRole r ON u.role_id = r.role_id\r\n"
			+ "LEFT JOIN Office o ON u.user_id = o.user_id\r\n"
			+ "ORDER BY u.create_date DESC\r\n" , nativeQuery = true)
	List<Object[]> findAllEmployee();
	
	@Query("SELECT u.userId, u.email, u.password, u.fullName, u.phone, u.createDate, r.name, eo.status, eo.office.officeId " +
		       "FROM User u " +
		       "JOIN u.role r " +
		       "LEFT JOIN EmployeeOffice eo ON eo.user.userId = u.userId " +
		       "WHERE ((:role = '' OR r.name LIKE CONCAT('%', :role, '%')) AND r.name NOT LIKE CONCAT('%', 'user', '%')) " +
		       "AND (:role = 'admin' OR (:office IS NULL OR eo.office.officeId = :office)) " +
		       "ORDER BY u.createDate DESC")
	List<Object[]> findByRoleOffice(@Param("role") String role, @Param("office") Integer office);
	
	boolean existsByEmail(String email);
	
    User findByEmail(String email);

    @Query("SELECT p.productId, p.name, p.image, p.weight, parcel.createDate, parcel.completeDate, pd.quantity, p.description, parcel.status " +
            "FROM User u " +
            "JOIN u.createdParcels parcel " +
            "JOIN parcel.parcelDetails pd " +
            "JOIN pd.product p " +
            "WHERE u.userId = :userId")
    List<Object[]> findProductDetailsForUser(@Param("userId") Integer userId);
    @Query("""
    SELECT 
        parcel.parcelId AS parcelId, 
        pr.name AS productName, 
        pr.image AS productImage, 
        pr.weight AS productWeight, 
        parcel.createDate AS createDate, 
        parcel.completeDate AS completeDate, 
        pd.quantity AS quantity, 
        pr.description AS productDescription, 
        sf.fee AS shippingFee, pr.money AS money,
        (pd.quantity * pr.money) + sf.fee AS totalAmount, 
        pm.status AS paymentStatus
    FROM User u
    JOIN u.createdParcels parcel
    JOIN parcel.parcelDetails pd
    JOIN pd.product pr
    JOIN parcel.shippingType st
    JOIN st.shippingFees sf
    JOIN parcel.paymentMethod pm
    WHERE u.userId = :userId 
      AND pm.status = false
    """)
    List<Map<String, Object>> findUnpaidParcelsByUserId(@Param("userId") Integer userId);
}

