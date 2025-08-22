//package vn.iostar.service.User;
//
//
//import jakarta.annotation.Nonnull;
//import jakarta.persistence.EntityNotFoundException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
//import vn.iostar.entity.Parcel;
//import vn.iostar.entity.User;
//import vn.iostar.model.ParcelRouteModel;
//import vn.iostar.repository.ParcelRepository;
//import vn.iostar.repository.UserRepository;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//// handle user edit - delete- update!
//public class UserFunctionServiceImpl {
//    @Autowired
//    UserRepository userRepository;
//    @Autowired
//    ClientServiceImpl clientService;
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//    @Autowired
//    ParcelRepository parcelRepository;
//
//    public User checkEmailExist(String email){
//        User user = userRepository.findByEmail(email);
//        if(userRepository.existsByEmail(email)){
//            return user;
//        }else {
//            return null;
//        }
//    }
//    public void updatePassword(String email){
//        User user = userRepository.findByEmail(email);
//        String newPassword = clientService.create(user);
//        user.setPasswordHash(newPassword);
//        user.setPasswordHash(passwordEncoder.encode(newPassword));
//        userRepository.save(user);
//    }
////    public ParcelRouteModel print(String landingCode){
////        Parcel parcel = parcelRepository.findParcelWithDetails(landingCode);
////        if (parcel == null) {
////            throw new EntityNotFoundException("Parcel not found with ladingCode: " + landingCode);
////        }
////        // Ánh xạ RouteHistory thành RouteDetail
//////        List<ParcelRouteModel.RouteDetail> routeDetails = parcel.getRouteHistories().stream().map(rh ->
//////                new ParcelRouteModel.RouteDetail(
//////                        rh.getCheckinTime().toString(),
//////                        rh.getCheckoutTime() != null ? rh.getCheckoutTime().toString() : null,
//////                        rh.getPostOffice().getAddress(),
//////                        rh.getNote()
//////                )
//////        ).collect(Collectors.toList());
//////        return new ParcelRouteModel(
//////                parcel.getLadingCode(),
//////                parcel.getUser().getFullName(),
//////                parcel.getStatus(),
//////                routeDetails
//////        );
////    }
//    public Boolean handleChangePassword(String email, String password){
//        User user = userRepository.findByEmail(email);
//        if (passwordEncoder.matches(password, user.getPasswordHash())) {
//            return false;
//        }else {
//            user.setPasswordHash(passwordEncoder.encode(password));
//            userRepository.save(user);
//            return true;
//        }
//    }
//    public User updateUser(User user){
//        System.out.println("User ID: " + user.getUserId());
//        return userRepository.save(user);
//    }
//    public User getUser(String email){
//        return userRepository.findByEmail(email);
//    }
////    public List<Object[]> getUserWithParcelsAndProducts(Integer userId) {
////        return userRepository.findProductDetailsForUser(userId);
////    }
//}
