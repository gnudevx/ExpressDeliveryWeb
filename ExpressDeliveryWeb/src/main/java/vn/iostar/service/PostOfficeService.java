//package vn.iostar.service;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//
//import vn.iostar.entity.PostOffice;
//import vn.iostar.repository.PostOfficeRepository;
//
//@Service
//public class PostOfficeService implements IPostOfficeService{
//	@Autowired
//	PostOfficeRepository officeRepository;
//
//	public PostOfficeService(PostOfficeRepository officeRepository) {
//		this.officeRepository = officeRepository;
//	}
//
//	@Override
//	public <S extends PostOffice> S save(S entity) {
//		return officeRepository.save(entity);
//	}
//
//	@Override
//	public List<PostOffice> findAll() {
//		return officeRepository.findAll();
//	}
//
//	@Override
//	public List<PostOffice> findAllById(Iterable<Integer> ids) {
//		return officeRepository.findAllById(ids);
//	}
//
//	@Override
//	public Optional<PostOffice> findById(Integer id) {
//		return officeRepository.findById(id);
//	}
//
//	@Override
//	public long count() {
//		return officeRepository.count();
//	}
//
//	@Override
//	public void deleteById(Integer id) {
//		officeRepository.deleteById(id);
//	}
//
//	@Override
//	public Page<PostOffice> getAll(Integer pageNo) {
//		Pageable pageable = PageRequest.of(pageNo - 1, 10);
//		return officeRepository.findAll(pageable);
//	}
//	 @Override
//		public PostOffice getById(Integer id) {
//			return officeRepository.getById(id);
//		}
//}