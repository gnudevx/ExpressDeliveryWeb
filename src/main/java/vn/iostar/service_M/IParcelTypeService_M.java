//package vn.iostar.service_M;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//
//import vn.iostar.entity.ParcelType;
//import vn.iostar.entity.User;
//import vn.iostar.models.ParcelDTO;
//
//public interface IParcelTypeService_M {
//
//	void deleteAll();
//
//	void delete(ParcelType entity);
//
//	void deleteById(Integer id);
//
//	Optional<ParcelType> findById(Integer id);
//
//	List<ParcelType> findAll();
//
//	Page<ParcelType> findAll(Pageable pageable);
//
//	List<ParcelType> findAll(Sort sort);
//
//	<S extends ParcelType> S save(S entity);
//
//	long count();
//
//	ParcelType findParcelType(Float weight);
//
//	ParcelType getById(Integer id);
//
//}
