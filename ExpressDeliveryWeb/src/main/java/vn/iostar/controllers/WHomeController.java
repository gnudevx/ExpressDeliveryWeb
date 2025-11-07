//package vn.iostar.controllers;
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpSession;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//import vn.iostar.entity.User;
//import vn.iostar.service.User.CreateUsersServiceImpl;
//import vn.iostar.service.User.UserFunctionServiceImpl;
//
//import java.util.List;
//
//
//@Controller
//@RequestMapping("/home")
//public class WHomeController {
//	@Autowired
//	CreateUsersServiceImpl userService;
//	@Autowired
//	UserFunctionServiceImpl functionService;
//
//	@GetMapping("/home_user")
//	public String homeUser(HttpSession session, Model model)
//	{
//		User user = (User)session.getAttribute("user");
//		model.addAttribute("user",user);
//		if(user!= null) {
//			session.setAttribute("user", user);
//			System.out.println("sessionnn home:" + user.getUserId() + " role?" + user.getRole().getRoleName());
////			List<Object[]> productDetails = functionService.getUserWithParcelsAndProducts(user.getUserId());
////			model.addAttribute("productDetails", productDetails);
//		}
//		return "/user/home-user";
//	}
//	@GetMapping("/home_shipper")
//	public String homeShipper(Model model)
//	{
//		List<User> categories = userService.findAll();
//		model.addAttribute("list", categories);
//		return "/admin/home-admin";
//	}
//	@GetMapping("/Contact_manager")
//	public String loadChat() {
//		return "user/Contact_manager";
//	}
//
//}