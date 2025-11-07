//package vn.iostar.repository;
//
//import java.time.LocalDate;
//import java.util.List;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//import vn.iostar.entity.Parcel;
//
//@Repository
//public interface shipperRepository extends JpaRepository<Parcel, Integer> {
//	@Query("SELECT p FROM Parcel p WHERE p.shipper.userId = :shipperId")
//	List<Parcel> findParcelsByShipperId(@Param("shipperId") Integer shipperId);
//
//	@Query("SELECT p FROM Parcel p WHERE p.shipper.userId = :shipperId AND p.status = :status")
//	List<Parcel> findParcelsByStatusAndShipperId(@Param("shipperId") Integer shipperId, @Param("status") String status);
//
//
//	@Query("SELECT p FROM Parcel p WHERE p.shipper.userId = :shipperId " +
//		       "AND (:status IS NULL OR p.status = :status) " +
//		       "AND (:startDate IS NULL OR p.createDate >= :startDate) " +
//		       "AND (:completeDate IS NULL OR p.createDate <= :completeDate)")
//		List<Parcel> findParcelsByFilters(
//		    @Param("shipperId") Integer shipperId,
//		    @Param("status") String status,
//		    @Param("startDate") LocalDate startDate,
//		    @Param("completeDate") LocalDate completeDate
//		);
//
//}
