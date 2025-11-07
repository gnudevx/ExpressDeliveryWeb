//package vn.iostar.repository;
//
//import java.util.List;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//
//import vn.iostar.entity.Parcel;
//import vn.iostar.entity.PostOffice;
//import vn.iostar.models.ParcelDTO;
//
//public interface ParcelRepository_M extends JpaRepository<Parcel, Integer> {
//    // Bạn có thể thêm các phương thức truy vấn tùy chỉnh tại đây nếu cần
//	// Lấy danh sách Parcel chưa có shipper
//    List<Parcel> findByShipperIsNull();
//
//    @Query("SELECT DISTINCT p.status FROM Parcel p")
//    List<String> findAllDistinctStatuses(); // Phương thức để lấy tất cả trạng thái duy nhất
//
// // Tìm theo địa chỉ văn phòng gửi
//    List<Parcel> findByStartOffice_Address(String address);
//
//    // Hoặc nếu bạn muốn tìm theo ID
//    List<Parcel> findByStartOffice_OfficeId(Integer officeId);
//
//    List<ParcelDTO> findByStartOffice(PostOffice postOffice);
//}
