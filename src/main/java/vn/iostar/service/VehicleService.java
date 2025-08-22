//package vn.iostar.service;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//
//import vn.iostar.entity.PostOffice;
//import vn.iostar.entity.Vehicle;
//import vn.iostar.entity.VehicleType;
//import vn.iostar.repository.PostOfficeRepository;
//import vn.iostar.repository.VehicleRepository;
//import vn.iostar.repository.VehicleTypeRepository;
//
//@Service
//public class VehicleService implements IVehicleService{
//	@Autowired
//	VehicleRepository vehicleRepository;
//
//	public VehicleService(VehicleRepository vehicleRepository) {
//		this.vehicleRepository = vehicleRepository;
//	}
//
//	@Override
//	public <S extends Vehicle> S save(S entity) {
//		return vehicleRepository.save(entity);
//	}
//
//	@Override
//	public List<Vehicle> findAll() {
//		return vehicleRepository.findAll();
//	}
//
//	@Override
//	public List<Vehicle> findAllById(Iterable<Integer> ids) {
//		return vehicleRepository.findAllById(ids);
//	}
//
//	@Override
//	public Optional<Vehicle> findById(Integer id) {
//		return vehicleRepository.findById(id);
//	}
//
//	@Override
//	public long count() {
//		return vehicleRepository.count();
//	}
//
//	@Override
//	public void deleteById(Integer id) {
//		vehicleRepository.deleteById(id);
//	}
//
//	@Override
//	public Page<Vehicle> getAll(Integer pageNo) {
//		Pageable pageable = PageRequest.of(pageNo - 1, 10);
//		return vehicleRepository.findAll(pageable);
//	}
//}