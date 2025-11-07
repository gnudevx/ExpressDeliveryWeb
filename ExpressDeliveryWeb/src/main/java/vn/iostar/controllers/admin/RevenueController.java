//package vn.iostar.controllers.admin;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import vn.iostar.model.RevenueDTO;
//import vn.iostar.service.IRevenueService;
//
//@Controller
//@RequestMapping("/admin/revenue")
//public class RevenueController {
//	@Autowired(required = true)
////	IRevenueService revenueService;
//
//	@RequestMapping("")
//	public String list(ModelMap model, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo) {
//		Page<RevenueDTO> list = revenueService.getAll(pageNo);
//		model.addAttribute("revenue", list);
//		model.addAttribute("totalPage", list.getTotalPages() > 0 ? list.getTotalPages() : 1);
//		model.addAttribute("currentPage", pageNo);
//		if (list.isEmpty()) {
//			model.addAttribute("message", "Chưa có bưu cục nào để hiển thị. Vui lòng tiến hành tạo bưu cục!");
//		}
//		return "admin/revenue/list";
//	}
//}
