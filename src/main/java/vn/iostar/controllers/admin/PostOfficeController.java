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
//import vn.iostar.model.PostOfficeModel;
//import vn.iostar.service.IPostOfficeService;
//
//@Controller
//@RequestMapping("admin/offices")
//public class PostOfficeController {
//	@Autowired(required = true)
//	IPostOfficeService postOfficeService;
//
//	@RequestMapping("")
//	public String list(ModelMap model, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo) {
//		Page<PostOffice> list = postOfficeService.getAll(pageNo);
//			model.addAttribute("offices", list);
//			model.addAttribute("totalPage", list.getTotalPages() > 0 ? list.getTotalPages() : 1);
//			model.addAttribute("currentPage", pageNo);
//			if (list.isEmpty()) {
//				model.addAttribute("message", "Không có bưu cục nào để hiển thị!");
//			}
//		return "admin/offices/list";
//	}
//
//	@RequestMapping("/add")
//	public String add(ModelMap model) {
//		PostOfficeModel officeModel = new PostOfficeModel();
//		officeModel.setIsEdit(false);
//		model.addAttribute("office", officeModel);
//		return "admin/offices/addOrEdit";
//	}
//
//	@GetMapping("edit/{id}")
//	public ModelAndView edit(ModelMap model, @PathVariable("id") Integer officeId) {
//		Optional<PostOffice> office = postOfficeService.findById(officeId);
//		PostOfficeModel officeModel = new PostOfficeModel();
//		if (office.isPresent()) {
//			PostOffice entity = office.get();
//			BeanUtils.copyProperties(entity, officeModel);
//			officeModel.setIsEdit(true);
//			model.addAttribute("office", officeModel);
//			return new ModelAndView("admin/offices/addOrEdit", model);
//		}
//		model.addAttribute("message", "Bưu cục đã tồn tại!");
//		return new ModelAndView("forward:/admin/offices", model);
//	}
//
//	@PostMapping("saveOrUpdate")
//	public ModelAndView saveOrUpdate(ModelMap model, @Valid @ModelAttribute("office") PostOfficeModel officeModel,
//			BindingResult result) {
//		if (result.hasErrors()) {
//			return new ModelAndView("admin/offices/addOrEdit");
//		}
//		PostOffice entity = new PostOffice();
//		// copy từ Model sang Entity
//		BeanUtils.copyProperties(officeModel, entity);
//		// gọi hàm save trong service
//		postOfficeService.save(entity);
//		// đưa thông báo về cho biến message
//		String message = "";
//		if (officeModel.getIsEdit() == true) {
//			message = "Chỉnh sửa thành công!";
//		} else {
//			message = "Thêm bưu cục mới thành công!";
//		}
//		model.addAttribute("message", message);
//		// redirect về URL controller
//		return new ModelAndView("forward:/admin/offices", model);
//	}
//
//	@GetMapping("delete/{id}")
//	public ModelAndView delet(ModelMap model, @PathVariable("id") Integer officeId) {
//		try {
//			postOfficeService.deleteById(officeId);
//			model.addAttribute("message", "Xóa thành công!");
//		} catch (Exception e) {
//			model.addAttribute("message", "Không thể xóa vì bưu cục có liên quan đến nhiều bản ghi khác!");
//		}
//		return new ModelAndView("forward:/admin/offices", model);
//	}
//}
