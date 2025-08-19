package vn.iostar.service.shipper;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.iostar.entity.Parcel;
import vn.iostar.repository.ParcelRepository;
import vn.iostar.repository.shipperRepository;

@Service
public class ShipperService implements IShipperService{
	@Autowired 
	ParcelRepository parcelRepo ;
	@Autowired
	shipperRepository shipperrepo;
	
	
	@Override
	public List<Parcel> findParcelsByShipperId(Integer shipperId) {
		return shipperrepo.findParcelsByShipperId(shipperId);
	}


	@Override
	public Optional<Parcel> findById(int id) {
		return parcelRepo.findById(id);
	}


	@Override
	public List<Parcel> findParcelsByStatusAndShipperId(Integer shipperId, String status) {
		return shipperrepo.findParcelsByStatusAndShipperId(shipperId, status);
	}


	@Override
	public List<Parcel> findParcelsByFilters(Integer shipperId, String status, LocalDate startDate,
			LocalDate completeDate) {
		return shipperrepo.findParcelsByFilters(shipperId, status, startDate, completeDate);
	}


	@Override
	public <S extends Parcel> S save(S entity) {
		return parcelRepo.save(entity);
	}
	

	

	


	
	
}
