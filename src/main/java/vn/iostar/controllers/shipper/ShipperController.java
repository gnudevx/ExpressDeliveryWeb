//package vn.iostar.controllers.shipper;
//
//import java.time.LocalDate;
//import java.util.List;
//import java.util.Optional;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.format.annotation.DateTimeFormat;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import jakarta.servlet.http.HttpSession;
//import vn.iostar.entity.Parcel;
//
//import vn.iostar.entity.User;
//
//import vn.iostar.service.shipper.IShipperService;
//
//@Controller
//@RequestMapping("shipper")
//public class ShipperController {
//	@Autowired
//	IShipperService shiperService;
//
//	@GetMapping("/listPacel")
//	public String index(HttpSession session, Model model) {
//		// Lấy đối tượng User từ session
//		User currentUser = (User) session.getAttribute("user");
//		// Kiểm tra nếu người dùng chưa đăng nhập
//		if (currentUser == null) {
//			return "/account/login_account"; // Chuyển hướng đến trang đăng nhập nếu chưa đăng nhập
//		}
//		// Lấy shipperId từ entity User
//		Integer shipperId = currentUser.getUserId();
//		// Lấy danh sách các đơn hàng dựa trên shipperId
//		List<Parcel> parcels = shiperService.findParcelsByShipperId(shipperId);
//		// Kiểm tra nếu không có đơn hàng
//		if (parcels == null || parcels.isEmpty()) {
//			model.addAttribute("message", "No parcels found.");
//		} else {
//			model.addAttribute("parcels", parcels); // Truyền danh sách đơn hàng ra view
//		}
//		return "shipper/ListParcel"; // Trả về view cho shipper
//	}
//	@GetMapping("/filterParcel")
//	public String filterParcels(
//	        @RequestParam(value = "status", required = false) String status,
//	        @RequestParam(value = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
//	        @RequestParam(value = "completeDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate completeDate,
//	        HttpSession session,
//	        Model model) {
//
////	    User currentUser = (User) session.getAttribute("currentUser");
////	    if (currentUser == null) {
////	        return "redirect:/security/login"; // Chuyển hướng đến trang đăng nhập nếu chưa đăng nhập
////	    }
//
////	    Integer shipperId = currentUser.getUserId();
//
//		Integer shipperId = 2;
//	    // Kiểm tra nếu startDate hoặc endDate không có giá trị
//	    if (startDate == null && completeDate == null) {
//	        // Nếu không có ngày, tìm tất cả đơn hàng theo trạng thái (nếu có)
//	        List<Parcel> parcels = shiperService.findParcelsByStatusAndShipperId(shipperId, status);
//	        model.addAttribute("parcels", parcels);
//	    } else {
//	        // Nếu có ngày, thực hiện lọc theo ngày
//	        List<Parcel> parcels = shiperService.findParcelsByFilters(shipperId, status, startDate, completeDate);
//	        model.addAttribute("parcels", parcels);
//	    }
//
//	    model.addAttribute("status", status);
//	    model.addAttribute("startDate", startDate);
//	    model.addAttribute("completeDate", completeDate);
//
//	    return "shipper/ListParcel"; // Trả về view cho shipper
//	}
//
//
//	@GetMapping("detail/{id}")
//	public ModelAndView detail(@PathVariable("id") Integer parcelId, ModelMap model) {
//		// Tìm đơn hàng theo ID
//		Optional<Parcel> optionalParcel = shiperService.findById(parcelId);
//		if (optionalParcel.isPresent()) {
//			Parcel parcel = optionalParcel.get();
//			model.addAttribute("parcel", parcel);
//			return new ModelAndView("shipper/ParcelDetail", model); // Trả về view chi tiết
//		}
//		// Nếu không tìm thấy, chuyển hướng về danh sách đơn hàng
//		model.addAttribute("error", "Parcel not found.");
//		return new ModelAndView("redirect:/shipper/listParcel", model);
//	}
//	@PostMapping("/updateStatus")
//    public String updateStatus(@RequestParam("parcelId") int parcelId,
//                               @RequestParam("status") String status,
//                               RedirectAttributes redirectAttributes) {
//        // Lấy thông tin đơn hàng từ cơ sở dữ liệu
//        Parcel parcel = shiperService.findById(parcelId).orElse(null);
//
//        if (parcel == null) {
//            // Nếu không tìm thấy đơn hàng, thêm thông báo lỗi
//            redirectAttributes.addFlashAttribute("errorMessage", "Không tìm thấy đơn hàng với ID: " + parcelId);
//            return "redirect:/shipper/listPacel";
//        }
//
//        // Cập nhật trạng thái
//        parcel.setStatus(status);
//
//        // Lưu thay đổi
//        shiperService.save(parcel);
//
//        // Thêm thông báo thành công
//        redirectAttributes.addFlashAttribute("successMessage", "Cập nhật trạng thái thành công cho đơn hàng: " + parcelId);
//
//        return "redirect:/shipper/listPacel";
//    }
//}