package vn.iostar.service.PaymentService;

import vn.iostar.entity.PaymentMethod;

import java.util.List;
import java.util.Optional;

public interface IPayMethodService {

    List<PaymentMethod> findAll();

    List<PaymentMethod> findAllById(Iterable<String> ids);

    Optional<PaymentMethod> findById(String id);

    <S extends PaymentMethod> S save(S entity);
}
