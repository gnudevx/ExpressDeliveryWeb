package vn.iostar.service_M;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import vn.iostar.entity.ShippingType;
import vn.iostar.entity.User;

public interface IShippingTypeService_M {

	void deleteAll();

	void deleteById(Integer id);

	long count();

	Optional<ShippingType> findById(Integer id);

	List<ShippingType> findAll();

	Page<ShippingType> findAll(Pageable pageable);

	List<ShippingType> findAll(Sort sort);

	<S extends ShippingType> S save(S entity);

	ShippingType getById(Integer id);
}
