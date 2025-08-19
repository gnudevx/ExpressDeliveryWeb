package vn.iostar.controllers.admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.iostar.service.IParcelService;
import vn.iostar.service.IUserService;

@Controller
@RequestMapping("/admin")
public class DashboardController {
	
	@Autowired(required = true)
	IParcelService parcelService;
	
	@Autowired(required = true)
	IUserService userService;
	
	@RequestMapping("/dashboard")
	public String dashboard(ModelMap model) {
		model.addAttribute("parDay", parcelService.parcelOnCurrentDate());
		model.addAttribute("parWeek", parcelService.parcelOnCurrentWeek());
		model.addAttribute("parMonth", parcelService.parcelOnCurrentMonth());
		model.addAttribute("parYear", parcelService.parcelOnCurrentYear());
		model.addAttribute("totalMonthlyParcel", parcelService.getMonthlyTotalParcel());
		model.addAttribute("totalQuarterParcel",parcelService.getQuarterlyTotalParcel());
		
		model.addAttribute("reDay",parcelService.revenueOnCurrentDate());
		model.addAttribute("reWeek",parcelService.revenueOnCurrentWeek());
		model.addAttribute("reMonth",parcelService.revenueOnCurrentMonth());
		model.addAttribute("reYear",parcelService.revenueOnCurrentYear());
		model.addAttribute("totalMonthlyRevenue", parcelService.getMonthlyTotalRevenue());
		model.addAttribute("totalQuarterRevenue",parcelService.getQuarterlyTotalRevenue());
		
		model.addAttribute("cusDay", userService.countNewCustomerDay());
		model.addAttribute("cusMonth", userService.countNewCustomerMonth());
		model.addAttribute("cusYear", userService.countNewCustomerYear());
		model.addAttribute("cusTotal", userService.countTotalCustomer());
		model.addAttribute("totalMonthlyCustomer", userService.getMonthlyTotal());
		model.addAttribute("totalQuarterCustomer",userService.getQuarterlyTotal());

		return "admin/dashboard";
	}
}
