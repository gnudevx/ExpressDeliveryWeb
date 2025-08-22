package vn.iostar.service_M;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

//import vn.iostar.entity.ParcelType;
import vn.iostar.entity.Role;
import vn.iostar.entity.User;

public interface IRoleService_M {

	void deleteAll();

	void deleteAllById(Iterable<? extends Integer> ids);

	void delete(Role entity);

	Optional<Role> findById(Integer id);

	List<Role> findAll();

	<S extends Role> S save(S entity);

	long count();

	
}
