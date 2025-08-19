package vn.iostar.service.PaymentService;

import java.util.Map;

public interface IPaymentService {
    Map<String, Object> getUnpaidParcelsWithTotal(Integer userId);
}
