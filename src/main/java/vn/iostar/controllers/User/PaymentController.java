package vn.iostar.controllers.User;


import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.iostar.entity.User;
import vn.iostar.service.PaymentService.IPayMethodService;
import vn.iostar.service.PaymentService.IPaymentService;

import java.util.Map;

@Controller
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    IPayMethodService payMethodService;
    @Autowired
    IPaymentService paymentService;

    @GetMapping
    public String showPaymentForm() {
        return "payment_form";
    }

    @GetMapping("/create_payment") // Xử lý thanh toán
    public String createPayment( HttpSession session, Model model) {
        User user = (User)session.getAttribute("user");
        if(user == null) {
        	System.out.println("ádsadsad");;
        }
        model.addAttribute("user", user);
        Map<String, Object> data1 = paymentService.getUnpaidParcelsWithTotal(user.getUserId());
        // Gửi dữ liệu qua model để render giao diện
        if(data1.get("parcels").equals(null)) {
        	System.out.println("1231232");
        }
        model.addAttribute("parcels", data1.get("parcels"));
        model.addAttribute("totalAmount", data1.get("totalAmount"));

        // Chuyển hướng tới trang thành công
        return "/Payment/Payment_Order";
    }

    @GetMapping("/payment/success") // Trang thông báo thanh toán thành công
    public String paymentSuccess() {
        return "payment_success";
    }

}
