package vn.iostar.controllers.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.validation.Valid;
import vn.iostar.entity.*;
import vn.iostar.models.ParcelTypeModel;
import vn.iostar.repository.ParcelTypeRepository_M;
import vn.iostar.service_M.IParcelTypeService_M;

@Controller
@RequestMapping("admin/management/shippingManagement/ParcelType")
public class ParcelTypeController {
	@Autowired
	IParcelTypeService_M parcelTypeService;
	/*
	 * @RequestMapping("") public String listUsers(ModelMap model) { List<User>
	 * listUser = userService.findAll(); model.addAttribute("listUser", listUser);
	 * return "admin/management/userManagement/listUser"; }
	 */
	
	@Autowired
	private ParcelTypeRepository_M parcelTypeRepository;  // Đảm bảo tên chính xác là UserRepository

	@RequestMapping("")
    public String listUsers(ModelMap model) {
        // Đếm số lượng người dùng trong cơ sở dữ liệu
        long userCount = parcelTypeRepository.count();  
        
        // In ra log số lượng người dùng
        System.out.println("Total Users tessttttt: " + userCount);  // In số lượng ra console/log

        // Thêm số lượng người dùng vào model để truyền đến view
        model.addAttribute("userCount", userCount);

        // Lấy danh sách người dùng
        List<ParcelType> user = parcelTypeService.findAll();
        model.addAttribute("listParcelTypes", user);

        return "admin/management/shippingManagement/listParcelTypes";  // Trả về view
    }
	
	@GetMapping("add")
	public String Add(ModelMap model)
	{
		ParcelTypeModel parcelType = new ParcelTypeModel();
		parcelType.setIsEdit(false);
		System.out.println("ParcelType: " + parcelType);
		model.addAttribute("parcelType", parcelType);
		return "admin/management/shippingManagement/AddOrEditParcelType";
	}
	@PostMapping("saveOrUpdate")
	public ModelAndView saveOrUpdate(ModelMap model, @Valid @ModelAttribute("parcelType") ParcelTypeModel parcelType, BindingResult result) {
		
		if(result.hasErrors()) {
			return new ModelAndView("admin/management/shippingManagement/AddOrEditParcelType");
		}
		ParcelType entity = new ParcelType();
		// copy từ model sang entity
		BeanUtils.copyProperties(parcelType, entity);
		
		parcelTypeService.save(entity);
		
		String message = "";
		if(parcelType.getIsEdit() == true)
		{
			message="Parcel Type đã được cập nhật";
		}else {
			message = "Parcel Type đã được thêm thành công";
		}
		model.addAttribute("message", message);
		return new ModelAndView("forward:/admin/management/shippingManagement/ParcelType", model);
	}
	
	@GetMapping("edit/{parcelTypeId}")
	public ModelAndView edit(ModelMap model, @PathVariable("parcelTypeId") Integer parcelTypeId) {
		Optional<ParcelType> opt = parcelTypeService.findById(parcelTypeId);
		ParcelTypeModel parcelTypeModel = new ParcelTypeModel();
		if(opt.isPresent()) {
			ParcelType entity = opt.get();
			BeanUtils.copyProperties(entity, parcelTypeModel);
			parcelTypeModel.setIsEdit(true);
			model.addAttribute("parcelType", parcelTypeModel);
			return new ModelAndView("admin/management/shippingManagement/AddOrEditParcelType", model);
		}
		model.addAttribute("message", "Parcel Type không tồn tại!");
		return new ModelAndView("admin/management/shippingManagement/ParcelType", model);
	}
	@GetMapping("delete/{parcelTypeId}")
	public ModelAndView delete(ModelMap model, @PathVariable("parcelTypeId") Integer parcelTypeId) {
		parcelTypeService.deleteById(parcelTypeId);
		model.addAttribute("message", "Parcel Type đã được xóa thành công!");
		return new ModelAndView("forward:/admin/management/shippingManagement/ParcelType", model);
	}
}
