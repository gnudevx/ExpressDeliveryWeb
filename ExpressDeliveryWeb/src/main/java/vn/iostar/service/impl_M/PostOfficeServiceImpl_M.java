//package vn.iostar.service.impl_M;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import vn.iostar.entity.PostOffice;
//import vn.iostar.repository.PostOfficeRepository_M;
//import vn.iostar.service_M.IPostOfficeService_M;
//
//@Service
//public class PostOfficeServiceImpl_M implements IPostOfficeService_M{
//
//	@Autowired
//    private PostOfficeRepository_M postOfficeRepository;
//
//    @Override
//	public List<PostOffice> getAllPostOffices() {
//        return postOfficeRepository.findAll();
//    }
//
////    @Override
////	public List<String> getAllAddresses() {
////        return postOfficeRepository.findAllAddresses(); // Lấy tất cả địa chỉ
////    }
////
////    @Override
////    public List<String> getAllOptionAddress() {
////    	return postOfficeRepository.findAllAddresses();
////    }
//}
