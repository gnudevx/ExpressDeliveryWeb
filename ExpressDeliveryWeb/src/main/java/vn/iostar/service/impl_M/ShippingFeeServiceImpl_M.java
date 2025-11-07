//package vn.iostar.service.impl_M;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import vn.iostar.entity.ShippingFee;
////import vn.iostar.entity.ShippingType;
//import vn.iostar.repository.ShippingFeeRepository_M;
//import vn.iostar.service_M.IShippingFeeService_M;
//
//@Service
//public class ShippingFeeServiceImpl_M implements IShippingFeeService_M{
//
//	@Autowired
//    private ShippingFeeRepository_M shippingFeeRepository;
//
//    @Override
//	public List<ShippingFee> getAllShippingFees() {
//        return shippingFeeRepository.findAll();
//    }
//
//	@Override
//	public <S extends ShippingFee> S save(S entity) {
//		return shippingFeeRepository.save(entity);
//	}
//
//	@Override
//	public long count() {
//		return shippingFeeRepository.count();
//	}
//
//	@Override
//	public void deleteById(ShippingFeeId id) {
//		shippingFeeRepository.deleteById(id);
//	}
//
//	@Override
//	public void delete(ShippingFee entity) {
//		shippingFeeRepository.delete(entity);
//	}
//
//	@Override
//    public boolean existsById(ShippingFeeId id) {
//        return shippingFeeRepository.existsById(id);
//    }
//
//	@Override
//	public Optional<ShippingFee> findById(ShippingFeeId id) {
//		return shippingFeeRepository.findById(id);
//	}
//
//	@Override
//    public void update(ShippingFee shippingFee) {
//        if (shippingFee != null && shippingFee.getId() != null) {
//            // Check if the ShippingFee exists in the database
//            if (shippingFeeRepository.existsById(shippingFee.getId())) {
//                // Save the updated ShippingFee entity to the database
//                shippingFeeRepository.save(shippingFee);
//            } else {
//                throw new IllegalArgumentException("ShippingFee not found for update");
//            }
//        } else {
//            throw new IllegalArgumentException("Invalid ShippingFee or ShippingFee ID");
//        }
//    }
//	@Override
//	public Integer findShippingFee(Integer parcelTypeId, Integer shippingTypeId) {
//		return shippingFeeRepository.findShippingFee(parcelTypeId, shippingTypeId);
//	}
//
//	@Override
//	public ShippingType getById(Integer id) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//
//}
