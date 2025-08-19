package vn.iostar.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.iostar.entity.PostOffice;
import vn.iostar.entity.VehicleType;
import vn.iostar.repository.PostOfficeRepository;
import vn.iostar.repository.VehicleTypeRepository;

@Service
public class VehicleTypeService implements IVehicleTypeService{
	@Autowired
	VehicleTypeRepository typeRepository;

	public VehicleTypeService(VehicleTypeRepository typeRepository) {
		this.typeRepository = typeRepository;
	}

	@Override
	public <S extends VehicleType> S save(S entity) {
		return typeRepository.save(entity);
	}

	@Override
	public List<VehicleType> findAll() {
		return typeRepository.findAll();
	}

	@Override
	public List<VehicleType> findAllById(Iterable<Integer> ids) {
		return typeRepository.findAllById(ids);
	}

	@Override
	public Optional<VehicleType> findById(Integer id) {
		return typeRepository.findById(id);
	}

	@Override
	public long count() {
		return typeRepository.count();
	}

	@Override
	public void deleteById(Integer id) {
		typeRepository.deleteById(id);
	}

	@Override
	public Page<VehicleType> getAll(Integer pageNo) {
		Pageable pageable = PageRequest.of(pageNo - 1, 10);
		return typeRepository.findAll(pageable);
	}

	@Override
	public VehicleType getById(Integer id) {
		return typeRepository.getById(id);
	}
}