package vn.iostar.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import vn.iostar.entity.PostOffice;
import vn.iostar.entity.Vehicle;
import vn.iostar.entity.VehicleType;

public interface IVehicleService {
	
	void deleteById(Integer id);

	long count();

	Optional<Vehicle> findById(Integer id);

	List<Vehicle> findAllById(Iterable<Integer> ids);

	List<Vehicle> findAll();

	<S extends Vehicle> S save(S entity);
	
	Page<Vehicle> getAll(Integer pageNo);

}