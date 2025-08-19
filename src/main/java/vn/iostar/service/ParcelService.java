package vn.iostar.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.iostar.entity.Parcel;
import vn.iostar.repository.ParcelRepository;

@Service
public class ParcelService implements IParcelService{
	@Autowired
	ParcelRepository parcelRepository;
	
	public ParcelService(ParcelRepository parcelRepository) {
		this.parcelRepository = parcelRepository;
	}

	@Override
	public long revenueOnCurrentDate() {
		return parcelRepository.revenueOnCurrentDate();
	}

	@Override
	public long revenueOnCurrentWeek() {
		return parcelRepository.revenueOnCurrentWeek();
	}

	@Override
	public long revenueOnCurrentMonth() {
		return parcelRepository.revenueOnCurrentMonth();
	}

	@Override
	public long revenueOnCurrentYear() {
		return parcelRepository.revenueOnCurrentYear();
	}

	@Override
	public List<Long> getMonthlyTotalRevenue() {
		return parcelRepository.getMonthlyTotalRevenue();
	}

	@Override
	public List<Long> getQuarterlyTotalRevenue() {
		return parcelRepository.getQuarterlyTotalRevenue();
	}

	@Override
	public long parcelOnCurrentDate() {
		return parcelRepository.parcelOnCurrentDate();
	}

	@Override
	public long parcelOnCurrentWeek() {
		return parcelRepository.parcelOnCurrentWeek();
	}

	@Override
	public long parcelOnCurrentMonth() {
		return parcelRepository.parcelOnCurrentMonth();
	}

	@Override
	public long parcelOnCurrentYear() {
		return parcelRepository.parcelOnCurrentYear();
	}

	@Override
	public List<Long> getMonthlyTotalParcel() {
		return parcelRepository.getMonthlyTotalParcel();
	}

	@Override
	public List<Long> getQuarterlyTotalParcel() {
		return parcelRepository.getQuarterlyTotalParcel();
	}

	@Override
	public long countOneCustomer(Integer id) {
		return parcelRepository.countOneCustomer(id);
	}

	@Override
	public List<Parcel> findParcelOneCustomer(Integer id) {
		return parcelRepository.findParcelOneCustomer(id);
	}

	@Override
	public <S extends Parcel> S save(S entity) {
		return parcelRepository.save(entity);
	}

	@Override
	public List<Parcel> findAll() {
		return parcelRepository.findAll();
	}

	@Override
	public void deleteById(Integer id) {
		parcelRepository.deleteById(id);
	}
	@Override
	public Page<Parcel> getAll(Integer pageNo) {
		Pageable pageable = PageRequest.of(pageNo - 1, 10);
		return parcelRepository.findAll(pageable);
	}

	@Override
	public Optional<Parcel> findById(Integer id) {
		return parcelRepository.findById(id);
	}
}
