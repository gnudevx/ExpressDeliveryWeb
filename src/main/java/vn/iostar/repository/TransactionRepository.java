package vn.iostar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import vn.iostar.entity.PostOffice;

@Repository
public interface TransactionRepository extends JpaRepository<PostOffice, Integer>{
	
	@Query(value = "WITH Dayly AS (\r\n"
			+ "SELECT start_office_id, ISNULL(COUNT(parcel_id),0) AS Dayly\r\n"
			+ "FROM parcels\r\n"
			+ "WHERE CONVERT(DATE, create_date) = CONVERT(DATE, GETDATE())\r\n"
			+ "GROUP BY start_office_id),\r\n"
			+ "Monthly AS (\r\n"
			+ "SELECT start_office_id, ISNULL(COUNT(parcel_id), 0) AS Monthly\r\n"
			+ "FROM parcels\r\n"
			+ "WHERE MONTH(create_date) = MONTH(GETDATE()) AND YEAR(create_date) = YEAR(GETDATE())\r\n"
			+ "GROUP BY start_office_id),\r\n"
			+ "Quarterly AS (\r\n"
			+ "SELECT start_office_id, ISNULL(COUNT(parcel_id), 0) AS Quarterly\r\n"
			+ "FROM parcels\r\n"
			+ "WHERE DATEPART(QUARTER, create_date) = DATEPART(QUARTER, GETDATE()) AND YEAR(create_date) = YEAR(GETDATE())\r\n"
			+ "GROUP BY start_office_id),\r\n"
			+ "Yearly AS (\r\n"
			+ "SELECT start_office_id, ISNULL(COUNT(parcel_id), 0) AS Yearly\r\n"
			+ "FROM parcels\r\n"
			+ "WHERE YEAR(create_date) = YEAR(GETDATE())\r\n"
			+ "GROUP BY start_office_id),\r\n"
			+ "Office AS (\r\n"
			+ "SELECT office_id, address\r\n"
			+ "FROM post_offices)\r\n"
			+ "SELECT \r\n"
			+ "    o.office_id AS office_id,\r\n"
			+ "    o.address AS Address,\r\n"
			+ "    ISNULL(dr.Dayly, 0) AS Dayly,\r\n"
			+ "    ISNULL(mr.Monthly, 0) AS Monthly,\r\n"
			+ "    ISNULL(qr.Quarterly, 0) AS Quarterly,\r\n"
			+ "    ISNULL(yr.Yearly, 0) AS Yearly\r\n"
			+ "FROM Office o\r\n"
			+ "FULL OUTER JOIN Dayly dr ON o.office_id = dr.start_office_id\r\n"
			+ "FULL OUTER JOIN Monthly mr ON o.office_id = mr.start_office_id\r\n"
			+ "FULL OUTER JOIN Quarterly qr ON o.office_id = qr.start_office_id\r\n"
			+ "FULL OUTER JOIN Yearly yr ON o.office_id = yr.start_office_id;", nativeQuery = true)
	List<Object[]> findTransaction();
	
	@Query("SELECT m.name, p.parcelId, p.user.userId, p.createDate, p.completeDate, p.shippingFee " +
		       "FROM Parcel p " +
		       "JOIN p.paymentMethod m " +
		       "JOIN p.user u " +
		       "WHERE p.startOffice.id = :id " +
		       "ORDER BY p.createDate DESC")
	List<Object[]> findTransactionByIdStartOffice(@Param("id") int id);
}
