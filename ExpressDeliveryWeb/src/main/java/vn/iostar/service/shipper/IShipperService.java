package vn.iostar.service.shipper;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import vn.iostar.entity.Parcel;

public interface IShipperService {

	Optional<Parcel> findById(int id);

	List<Parcel> findParcelsByShipperId(Integer shipperId);

	List<Parcel> findParcelsByFilters(Integer shipperId, String status, LocalDate startDate, LocalDate completeDate);

	List<Parcel> findParcelsByStatusAndShipperId(Integer shipperId, String status);

	<S extends Parcel> S save(S entity);


	

	

	

}
