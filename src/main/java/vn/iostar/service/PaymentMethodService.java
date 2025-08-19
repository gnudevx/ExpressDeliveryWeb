package vn.iostar.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.iostar.entity.PaymentMethod;
import vn.iostar.entity.PostOffice;
import vn.iostar.repository.PaymentMethodRepository;
import vn.iostar.repository.PostOfficeRepository;

@Service
public class PaymentMethodService implements IPaymentMethodService{
	@Autowired
	PaymentMethodRepository paymentRepository;

	public PaymentMethodService(PaymentMethodRepository paymentRepository) {
		this.paymentRepository = paymentRepository;
	}

	@Override
	public <S extends PaymentMethod> S save(S entity) {
		return paymentRepository.save(entity);
	}

	@Override
	public List<PaymentMethod> findAll() {
		return paymentRepository.findAll();
	}

	@Override
	public List<PaymentMethod> findAllById(Iterable<Integer> ids) {
		return paymentRepository.findAllById(ids);
	}

	@Override
	public Optional<PaymentMethod> findById(Integer id) {
		return paymentRepository.findById(id);
	}

	@Override
	public long count() {
		return paymentRepository.count();
	}

	@Override
	public void deleteById(Integer id) {
		paymentRepository.deleteById(id);
	}

	@Override
	public Page<PaymentMethod> getAll(Integer pageNo) {
		Pageable pageable = PageRequest.of(pageNo - 1, 10);
		return paymentRepository.findAll(pageable);
	}
	 @Override
		public PaymentMethod getById(Integer id) {
			return paymentRepository.getById(id);
		}
}