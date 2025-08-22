//package vn.iostar.repository;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//import vn.iostar.entity.ShippingFee;
//import vn.iostar.entity.ShippingType;
//
//@Repository
//public interface ShippingFeeRepository_M extends JpaRepository<ShippingFee, ShippingFeeId> {
//
//	boolean existsById(ShippingFeeId id);
//	@Query(value = "SELECT fee FROM shipping_fee\r\n"
//			+ "  WHERE parcel_type_id = :parcelTypeId AND shipping_type_id = :shippingTypeId", nativeQuery = true)
//    Integer findShippingFee(@Param("parcelTypeId") Integer parcelTypeId, @Param("shippingTypeId") Integer shippingTypeId);
//
//
//
//
//}
