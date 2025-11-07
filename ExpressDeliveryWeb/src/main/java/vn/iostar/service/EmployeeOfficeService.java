//package vn.iostar.service;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import vn.iostar.entity.EmployeeOffice;
//import vn.iostar.repository.EmployeeOfficeRepository;
//import vn.iostar.repository.ParcelRepository;
//
//@Service
//public class EmployeeOfficeService implements IEmployeeOfficeService{
//	@Autowired
//	EmployeeOfficeRepository eoRepository;
//
//	public EmployeeOfficeService(EmployeeOfficeRepository eoRepository) {
//		this.eoRepository = eoRepository;
//	}
//
//	@Override
//	public <S extends EmployeeOffice> S save(S entity) {
//		return eoRepository.save(entity);
//	}
//
//	@Override
//	public void deleteByUserId(Integer id) {
//		eoRepository.deleteByUserId(id);
//	}
//
//	@Override
//	public Optional<EmployeeOffice> findByUserIdAndOfficeId(Integer userId, Integer officeId) {
//		return eoRepository.findByUserIdAndOfficeId(userId, officeId);
//	}
//
//	@Override
//	public EmployeeOffice findByUserId(Integer userId) {
//		return eoRepository.findByUserId(userId);
//	}
//
//	@Override
//	public void delete(EmployeeOffice employeeOffice) {
//		eoRepository.delete(employeeOffice);
//	}
//
//}
