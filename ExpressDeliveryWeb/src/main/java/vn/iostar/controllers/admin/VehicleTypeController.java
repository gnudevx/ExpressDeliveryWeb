//package vn.iostar.controllers.admin;
//
//import java.util.Optional;
//
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.ModelAndView;
//
//import jakarta.validation.Valid;
//import vn.iostar.entity.PostOffice;
//import vn.iostar.entity.VehicleType;
//import vn.iostar.model.PostOfficeModel;
//import vn.iostar.model.VehicleTypeModel;
////import vn.iostar.service.IPostOfficeService;
//import vn.iostar.service.IVehicleTypeService;
//
//@Controller
//@RequestMapping("admin/vehicleTypes")
//public class VehicleTypeController {
////	@Autowired(required = true)
////	IVehicleTypeService typeService;
//
//	@RequestMapping("")
//	public String list(ModelMap model, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo) {
//		Page<VehicleType> list = typeService.getAll(pageNo);
//			model.addAttribute("list", list);
//			model.addAttribute("totalPage", list.getTotalPages() > 0 ? list.getTotalPages() : 1);
//			model.addAttribute("currentPage", pageNo);
//			if (list.isEmpty()) {
//				model.addAttribute("message", "Không có loại phương tiện nào để hiển thị!");
//			}
//		return "admin/vehicle-types/list";
//	}
//
//	@RequestMapping("/add")
//	public String add(ModelMap model) {
//		VehicleTypeModel typeModel = new VehicleTypeModel();
//		typeModel.setIsEdit(false);
//		model.addAttribute("type", typeModel);
//		return "admin/vehicle-types/addOrEdit";
//	}
//
//	@GetMapping("edit/{id}")
//	public ModelAndView edit(ModelMap model, @PathVariable("id") Integer Id) {
//		Optional<VehicleType> type = typeService.findById(Id);
//		VehicleTypeModel typeModel = new VehicleTypeModel();
//		if (type.isPresent()) {
//			VehicleType entity = type.get();
//			BeanUtils.copyProperties(entity, typeModel);
//			typeModel.setIsEdit(true);
//			model.addAttribute("type", typeModel);
//			return new ModelAndView("admin/vehicle-types/addOrEdit", model);
//		}
//		model.addAttribute("message", "Loại phương tiện vận chuyển đã tồn tại!");
//		return new ModelAndView("forward:/admin/vehicleTypes", model);
//	}
//
//	@PostMapping("saveOrUpdate")
//	public ModelAndView saveOrUpdate(ModelMap model, @Valid @ModelAttribute("type") VehicleTypeModel typeModel,
//			BindingResult result) {
//		if (result.hasErrors()) {
//			return new ModelAndView("admin/vehicle-types/addOrEdit");
//		}
//		VehicleType entity = new VehicleType();
//		// copy từ Model sang Entity
//		BeanUtils.copyProperties(typeModel, entity);
//		// gọi hàm save trong service
//		typeService.save(entity);
//		// đưa thông báo về cho biến message
//		String message = "";
//		if (typeModel.getIsEdit() == true) {
//			message = "Chỉnh sửa thành công!";
//		} else {
//			message = "Thêm loại phương tiện vận chuyển mới thành công!";
//		}
//		model.addAttribute("message", message);
//		// redirect về URL controller
//		return new ModelAndView("forward:/admin/vehicleTypes", model);
//	}
//
//	@GetMapping("delete/{id}")
//	public ModelAndView delet(ModelMap model, @PathVariable("id") Integer id) {
//		try {
//			typeService.deleteById(id);
//			model.addAttribute("message", "Xóa thành công!");
//		} catch (Exception e) {
//			model.addAttribute("message", "Không thể xóa vì loại phương tiện vận chuyển này có liên quan đến nhiều bản ghi khác!");
//		}
//		return new ModelAndView("forward:/admin/vehicleTypes", model);
//	}
//}