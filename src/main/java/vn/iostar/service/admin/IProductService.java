package vn.iostar.service.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import vn.iostar.entity.Product;

public interface IProductService {

	Page<Product> getAll(Integer pageNo);

	Optional<Product> findById(Integer id);

	List<Product> findAll();

	<S extends Product> S save(S entity);
	
	
}
