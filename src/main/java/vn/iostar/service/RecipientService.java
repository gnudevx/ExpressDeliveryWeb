package vn.iostar.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.iostar.entity.PostOffice;
import vn.iostar.entity.Recipient;
import vn.iostar.repository.PostOfficeRepository;
import vn.iostar.repository.RecipientRepository;

@Service
public class RecipientService implements IRecipientService{
	@Autowired
	RecipientRepository reRepository;

	public RecipientService(RecipientRepository reRepository) {
		this.reRepository = reRepository;
	}

	@Override
	public <S extends Recipient> S save(S entity) {
		return reRepository.save(entity);
	}

	@Override
	public List<Recipient> findAll() {
		return reRepository.findAll();
	}

	@Override
	public List<Recipient> findAllById(Iterable<Integer> ids) {
		return reRepository.findAllById(ids);
	}

	@Override
	public Optional<Recipient> findById(Integer id) {
		return reRepository.findById(id);
	}

	@Override
	public long count() {
		return reRepository.count();
	}

	@Override
	public void deleteById(Integer id) {
		reRepository.deleteById(id);
	}

	@Override
	public Page<Recipient> getAll(Integer pageNo) {
		Pageable pageable = PageRequest.of(pageNo - 1, 10);
		return reRepository.findAll(pageable);
	}

	@Override
	public Recipient getById(Integer id) {
		return reRepository.getById(id);
	}
}
