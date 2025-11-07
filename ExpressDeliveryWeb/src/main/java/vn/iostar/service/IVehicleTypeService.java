package vn.iostar.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import vn.iostar.entity.PostOffice;
import vn.iostar.entity.VehicleType;

public interface IVehicleTypeService {
	
	void deleteById(Integer id);

	long count();

	Optional<VehicleType> findById(Integer id);

	List<VehicleType> findAllById(Iterable<Integer> ids);

	List<VehicleType> findAll();

	<S extends VehicleType> S save(S entity);
	
	Page<VehicleType> getAll(Integer pageNo);
	
	VehicleType getById(Integer id);

}