//package vn.iostar.controllers.admin;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.*;
//
//import vn.iostar.entity.ShippingFee;
//import vn.iostar.service_M.IShippingFeeService_M;
//@Controller
//@RequestMapping("/admin/management/shippingFees")
//public class ShippingFeeController {
//
//    @Autowired
//    private IShippingFeeService_M shippingFeeService;
//
//    // Lấy tất cả các phí vận chuyển
//    @RequestMapping("")
//    public String listShippingFees(ModelMap model) {
//        // Lấy danh sách phí vận chuyển
//        List<ShippingFee> shippingFees = shippingFeeService.getAllShippingFees();
//        model.addAttribute("listShippingFees", shippingFees);
//
//        return "admin/management/shippingManagement/listShippingFee";  // Trả về view danh sách phí vận chuyển
//    }
//
//}
//
//
