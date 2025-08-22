//package vn.iostar.repository;
//
//import java.util.List;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//
//import vn.iostar.entity.Parcel;
//import vn.iostar.entity.PostOffice;
//
//@Repository
//public interface PostOfficeRepository_M extends JpaRepository<PostOffice, Integer> {
//	PostOffice findByAddress(String address);
//
//	List<PostOffice> findAll();
//
////	@Query("SELECT p.address FROM PostOffice p") // Giả sử trường địa chỉ là 'address'
////    List<String> findAllAddresses();
//}
