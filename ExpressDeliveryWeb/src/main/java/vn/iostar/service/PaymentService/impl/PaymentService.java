//package vn.iostar.service.PaymentService.impl;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import vn.iostar.repository.UserRepository;
//import vn.iostar.service.PaymentService.IPaymentService;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Service
//public class PaymentService implements IPaymentService {
//    @Autowireda
//    UserRepository userRepository;
//
////    @Override
////    public Map<String, Object> getUnpaidParcelsWithTotal(Integer userId) {
//////        List<Map<String, Object>> unpaidParcels = userRepository.findUnpaidParcelsByUserId(userId);
////
////        // Tính tổng tiền
//////        double totalAmount = unpaidParcels.stream()
//////                .mapToDouble(parcel -> (Float) parcel.get("totalAmount"))
//////                .sum();
//////
//////        // Chuẩn bị dữ liệu trả về
//////        Map<String, Object> result = new HashMap<>();
//////        result.put("parcels", unpaidParcels);
////        result.put("totalAmount", totalAmount);
////        return result;
////    }
//
//}
