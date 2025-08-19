package vn.iostar.controllers.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.iostar.entity.*;
import vn.iostar.service_M.IShippingTypeService_M;

@Controller
@RequestMapping("admin/management/shippingManagement/shippingType")
public class ShippingTypeController {
	@Autowired
	IShippingTypeService_M shippingTypeService;
	
	@RequestMapping("")
    public String listUsers(ModelMap model) {
        // Đếm số lượng người dùng trong cơ sở dữ liệu
        long userCount = shippingTypeService.count();  
        
        // In ra log số lượng người dùng
        System.out.println("Total Users ooooooooo: " + userCount);  // In số lượng ra console/log

        // Thêm số lượng người dùng vào model để truyền đến view
        model.addAttribute("userCount", userCount);

        // Lấy danh sách người dùng
        List<ShippingType> user = shippingTypeService.findAll();
        model.addAttribute("listShippingTypes", user);

        return "admin/management/shippingManagement/listShippingTypes";  // Trả về view
	}
}
