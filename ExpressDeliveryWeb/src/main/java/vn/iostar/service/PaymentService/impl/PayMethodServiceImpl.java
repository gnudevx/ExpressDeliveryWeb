//package vn.iostar.service.PaymentService.impl;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import vn.iostar.repository.PaymethodRepository;
//import vn.iostar.service.PaymentService.IPayMethodService;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class PayMethodServiceImpl implements IPayMethodService {
//    @Autowired
//    PaymethodRepository payMethodRepository;
//
//
//    @Override
//    public List<PaymentMethod> findAll() {
//        return payMethodRepository.findAll();
//    }
//
//    @Override
//    public List<PaymentMethod> findAllById(Iterable<String> ids) {
//        return payMethodRepository.findAllById(ids);
//    }
//
//    @Override
//    public Optional<PaymentMethod> findById(String id) {
//        return payMethodRepository.findById(id);
//    }
//
//    @Override
//    public <S extends PaymentMethod> S save(S entity) {
//        return payMethodRepository.save(entity);
//    }
//
//}
//
