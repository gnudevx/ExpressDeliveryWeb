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
//import vn.iostar.entity.Vehicle;
//import vn.iostar.entity.VehicleType;
//import vn.iostar.model.PostOfficeModel;
//import vn.iostar.model.VehicleModel;
//import vn.iostar.model.VehicleTypeModel;
//import vn.iostar.service.IPostOfficeService;
//import vn.iostar.service.IVehicleService;
//import vn.iostar.service.IVehicleTypeService;
//
//@Controller
//@RequestMapping("admin/vehicles")
//public class VehicleController {
//	@Autowired(required = true)
//	IVehicleService vehicleService;
//	@Autowired(required = true)
//	IVehicleTypeService typeService;
//
//	@RequestMapping("")
//	public String list(ModelMap model, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo) {
//		Page<Vehicle> list = vehicleService.getAll(pageNo);
//			model.addAttribute("list", list);
//			model.addAttribute("totalPage", list.getTotalPages() > 0 ? list.getTotalPages() : 1);
//			model.addAttribute("currentPage", pageNo);
//			if (list.isEmpty()) {
//				model.addAttribute("message", "Không có phương tiện nào để hiển thị!");
//			}
//		return "admin/vehicles/list";
//	}
//
//	@RequestMapping("/add")
//	public String add(ModelMap model) {
//		VehicleModel vehicleModel = new VehicleModel();
//		vehicleModel.setIsEdit(false);
//		model.addAttribute("listType", typeService.findAll());
//		model.addAttribute("vehicle", vehicleModel);
//		return "admin/vehicles/addOrEdit";
//	}
//
//	@GetMapping("edit/{id}")
//	public ModelAndView edit(ModelMap model, @PathVariable("id") Integer Id) {
//		Optional<Vehicle> vehicle = vehicleService.findById(Id);
//		VehicleModel vehicleModel = new VehicleModel();
//		if (vehicle.isPresent()) {
//			Vehicle entity = vehicle.get();
//			BeanUtils.copyProperties(entity, vehicleModel);
//			vehicleModel.setIsEdit(true);
//			model.addAttribute("vehicle", vehicleModel);
//			model.addAttribute("listType", typeService.findAll());
//			model.addAttribute("vehicleTypeId", entity.getVehicleType().getVehicleTypeId());
//			return new ModelAndView("admin/vehicles/addOrEdit", model);
//		}
//		model.addAttribute("message", "Phương tiện vận chuyển đã tồn tại!");
//		return new ModelAndView("forward:/admin/vehicles", model);
//	}
//
//	@PostMapping("saveOrUpdate")
//	public ModelAndView saveOrUpdate(ModelMap model,
//			@Valid @ModelAttribute("vehicle") VehicleModel vehicleModel,
//			@RequestParam(name = "vehicleTypeId") Integer vehicleTypeId,
//			BindingResult result) {
//		if (result.hasErrors()) {
//			return new ModelAndView("admin/vehicles/addOrEdit");
//		}
//		Vehicle entity = new Vehicle();
//		vehicleModel.setVehicleType(typeService.getById(vehicleTypeId));
//		// copy từ Model sang Entity
//		BeanUtils.copyProperties(vehicleModel, entity);
//		// gọi hàm save trong service
//		vehicleService.save(entity);
//		// đưa thông báo về cho biến message
//		String message = "";
//		if (vehicleModel.getIsEdit() == true) {
//			message = "Chỉnh sửa thành công!";
//		} else {
//			message = "Thêm phương tiện vận chuyển mới thành công!";
//		}
//		model.addAttribute("message", message);
//		// redirect về URL controller
//		return new ModelAndView("forward:/admin/vehicles", model);
//	}
//
//	@GetMapping("delete/{id}")
//	public ModelAndView delet(ModelMap model, @PathVariable("id") Integer id) {
//		try {
//			vehicleService.deleteById(id);
//			model.addAttribute("message", "Xóa thành công!");
//		} catch (Exception e) {
//			model.addAttribute("message", "Không thể xóa vì phương tiện vận chuyển này có liên quan đến nhiều bản ghi khác!");
//		}
//		return new ModelAndView("forward:/admin/vehicles", model);
//	}
//}