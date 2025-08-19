package vn.iostar.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import vn.iostar.entity.PostOffice;
import vn.iostar.entity.Recipient;

public interface IRecipientService {
	
	void deleteById(Integer id);

	long count();

	Optional<Recipient> findById(Integer id);

	List<Recipient> findAllById(Iterable<Integer> ids);

	List<Recipient> findAll();

	<S extends Recipient> S save(S entity);
	
	Page<Recipient> getAll(Integer pageNo);
	
	Recipient getById(Integer id);

}
