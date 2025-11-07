package vn.iostar.controllers.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vn.iostar.entity.Parcel;
import vn.iostar.entity.User;
import vn.iostar.service.IParcelService;
import vn.iostar.service.IUserService;

@Controller
@RequestMapping("/admin/users")
public class CustomerController {
	
//	@Autowired(required = true)
//	IUserService userService;
//	@Autowired(required = true)
//	IParcelService parcelService;

	@RequestMapping("")
	public String list(ModelMap model, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo) {
//		Page<User> list = userService.getCustomer(pageNo);
//			model.addAttribute("users", list);
//			model.addAttribute("totalPage", list.getTotalPages() > 0 ? list.getTotalPages() : 1);
//			model.addAttribute("currentPage", pageNo);
//			if (list.isEmpty()) {
//				model.addAttribute("message", "Không có khách hàng nào để hiển thị!");
//			}
		return "admin/users/list";
	}
	
	@RequestMapping("/detail/{id}")
	public String detail(ModelMap model, @PathVariable("id") Integer userId) {
//		model.addAttribute("user", userService.getById(userId));
//		List<Parcel> list = parcelService.findParcelOneCustomer(userId);
		
//		Long totalFee = list.stream()
//                .mapToLong(Parcel::getShippingFee)  // Assuming getShippingFee() returns a double
//                .sum();
//		if (list.size()==0) {
//			model.addAttribute("message", "Khách hàng không có đơn hàng nào để hiển thị");
//		}
//		model.addAttribute("numberParcel", list.size());
//		model.addAttribute("total", totalFee);
//		model.addAttribute("list", list);
		return "admin/users/detail";
	}

}
