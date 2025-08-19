package vn.iostar.controllers.admin;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import jakarta.validation.Valid;
import vn.iostar.entity.EmployeeOffice;
import vn.iostar.entity.Parcel;
import vn.iostar.entity.PaymentMethod;
import vn.iostar.entity.PostOffice;
import vn.iostar.entity.Role;
import vn.iostar.entity.User;
import vn.iostar.model.UserModel;
import vn.iostar.model.UserRoleDTO;
import vn.iostar.service.IEmployeeOfficeService;
import vn.iostar.service.IPostOfficeService;
import vn.iostar.service.IRoleService;
import vn.iostar.service.IUserService;

@Controller
@RequestMapping("/admin/employees")
public class EmployeeController {

	@Autowired(required = true)
	IUserService userService;
	@Autowired(required = true)
	IPostOfficeService officeService;
	@Autowired(required = true)
	IRoleService roleService;
	@Autowired(required = true)
	IEmployeeOfficeService eoService;

	@RequestMapping("")
	public String list(ModelMap model, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo) {
		Page<UserRoleDTO> list = userService.getAllEmployee(pageNo);
		model.addAttribute("list", list);
		model.addAttribute("totalPage", list.getTotalPages() > 0 ? list.getTotalPages() : 1);
		model.addAttribute("currentPage", pageNo);
		if (list.isEmpty()) {
			model.addAttribute("message", "Không có nhân viên nào nào để hiển thị!");
		}
		List<Role> role = roleService.findExceptUser();
		model.addAttribute("roles", role);
		List<PostOffice> office = officeService.findAll();
		model.addAttribute("offices", office);
		return "admin/employees/list";
	}

	@RequestMapping("/filter")
	public String filter(@RequestParam(name = "role") String role,
			@RequestParam(name = "office", required = false) Integer officeId, ModelMap model,
			@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo) {
		if (officeId == null) {
			officeId = null;
		}
		Page<UserRoleDTO> list = userService.getAllByRoleOffice(role, officeId, pageNo);
		model.addAttribute("list", list);
		model.addAttribute("totalPage", list.getTotalPages() > 0 ? list.getTotalPages() : 1);
		model.addAttribute("currentPage", pageNo);
		if (list.isEmpty()) {
			model.addAttribute("message", "Không có nhân viên nào nào để hiển thị!");
		}
		List<Role> roles = roleService.findExceptUser();
		model.addAttribute("roles", roles);
		List<PostOffice> office = officeService.findAll();
		model.addAttribute("offices", office);
		return "admin/employees/list";
	}

	@RequestMapping("/addShipper")
	public String addShipper(ModelMap model) {
		UserModel shipperModel = new UserModel();
		shipperModel.setIsEdit(false);
		shipperModel.setStatus(true);
		model.addAttribute("employee", shipperModel);
		List<PostOffice> office = officeService.findAll();
		model.addAttribute("offices", office);
		model.addAttribute("roleId", roleService.findByName("SHIPPER").getRoleId());
		model.addAttribute("selectedOfficeId", office.get(0).getOfficeId());
		return "admin/employees/addOrEditShipper";
	}

	@RequestMapping("/addAdmin")
	public String addAdmin(ModelMap model) {
		UserModel adminModel = new UserModel();
		adminModel.setIsEdit(false);
		adminModel.setStatus(true);
		model.addAttribute("roleId", roleService.findByName("ADMIN").getRoleId());
		model.addAttribute("employee", adminModel);
		return "admin/employees/addOrEditAdmin";
	}

	@GetMapping("edit/{id}")
	public ModelAndView edit(ModelMap model, @PathVariable("id") Integer id) {
		Optional<User> user = userService.findById(id);
		UserModel userModel = new UserModel();
		if (user.isPresent()) {
			User entity = user.get();
			BeanUtils.copyProperties(entity, userModel);
			userModel.setIsEdit(true);
			model.addAttribute("employee", userModel);
			model.addAttribute("roleId", userModel.getRole().getRoleId());
			if (entity.getEmployOffice() != null) {
				model.addAttribute("officeId", entity.getEmployOffice().getOffice().getOfficeId());
				List<PostOffice> office = officeService.findAll();
				model.addAttribute("offices", office);
				model.addAttribute("selectedOfficeId", entity.getEmployOffice().getOffice().getOfficeId());
				return new ModelAndView("admin/employees/addOrEditShipper", model);
			} else {
				return new ModelAndView("admin/employees/addOrEditAdmin", model);
			}
		}
		model.addAttribute("message", "Nhân viên không tồn tại!");
		return new ModelAndView("forward:/admin/employees", model);
	}

	@PostMapping("saveOrUpdateAdmin")
	public ModelAndView saveOrUpdateAdmin(ModelMap model, @Valid @ModelAttribute("employee") UserModel userModel,
			@RequestParam("roleId") Integer roleId, BindingResult result) {
		if (result.hasErrors()) {
			model.addAttribute("message", "Dữ liệu không hợp lệ. Vui lòng kiểm tra lại!");
			List<Role> roles = roleService.findExceptUserShipper();
			model.addAttribute("roles", roles);
			List<PostOffice> office = officeService.findAll();
			model.addAttribute("offices", office);
			return new ModelAndView("admin/employees/addOrEditAdmin", model);
		}
		if (userService.existsByEmail(userModel.getEmail()) && userModel.getIsEdit() == false) {
			model.addAttribute("message", "Email này đã tồn tại trong hệ thống. Vui lòng chọn email khác!");
			List<Role> roles = roleService.findExceptUserShipper();
			model.addAttribute("roles", roles);
			List<PostOffice> office = officeService.findAll();
			model.addAttribute("offices", office);
			return new ModelAndView("admin/employees/addOrEditAdmin", model);
		}
		User entity = new User();
		BeanUtils.copyProperties(userModel, entity);
		Role role = roleService.getById(roleId);
		entity.setRole(role);
		if (userModel.getIsEdit() == false) {
			entity.setCreateDate(LocalDateTime.now());
		}
		userService.save(entity);
		String message = "";
		if (userModel.getIsEdit() == true) {
			message = "Chỉnh sửa thành công!";
		} else {
			message = "Thêm Admin mới thành công!";
		}
		model.addAttribute("message", message);
		List<Role> roles = roleService.findExceptUserShipper();
		model.addAttribute("roles", roles);
		List<PostOffice> office = officeService.findAll();
		model.addAttribute("offices", office);
		return new ModelAndView("forward:/admin/employees", model);
	}

	@PostMapping("saveOrUpdateShipper")
	public ModelAndView saveOrUpdateShipper(ModelMap model, @Valid @ModelAttribute("employee") UserModel userModel,
			@RequestParam("officeId") Integer officeId, @RequestParam("roleId") Integer roleId, BindingResult result) {
		if (result.hasErrors()) {
			model.addAttribute("message", "Dữ liệu không hợp lệ. Vui lòng kiểm tra lại!");
			List<Role> roles = roleService.findExceptUserShipper();
			model.addAttribute("roles", roles);
			List<PostOffice> office = officeService.findAll();
			model.addAttribute("offices", office);
			model.addAttribute("roleId", roleService.findByName("SHIPPER").getRoleId());
			return new ModelAndView("admin/employees/addOrEditShipper", model);
		}
		if (userService.existsByEmail(userModel.getEmail()) && userModel.getIsEdit() == false) {
			model.addAttribute("message", "Email này đã tồn tại trong hệ thống. Vui lòng chọn email khác!");
			List<Role> roles = roleService.findExceptUserShipper();
			model.addAttribute("roles", roles);
			List<PostOffice> office = officeService.findAll();
			model.addAttribute("offices", office);
			model.addAttribute("roleId", roleService.findByName("SHIPPER").getRoleId());
			return new ModelAndView("admin/employees/addOrEditShipper", model);
		}
		User entity = new User();
		BeanUtils.copyProperties(userModel, entity);
		Role role = roleService.getById(roleId);
		entity.setRole(role);
		if (userModel.getIsEdit() == false) {
			entity.setCreateDate(LocalDateTime.now());
		}
		userService.save(entity);
		Optional<PostOffice> officeOpt = officeService.findById(officeId);
		if (officeOpt.isPresent()) {
			PostOffice office = officeOpt.get();
			createEmployeeOffice(entity, office);
		}
		String message = "";
		if (userModel.getIsEdit() == true) {
			message = "Chỉnh sửa thành công!";
		} else {
			message = "Thêm Shipper mới thành công!";
		}
		model.addAttribute("message", message);
		List<Role> roles = roleService.findExceptUserShipper();
		model.addAttribute("roles", roles);
		List<PostOffice> office = officeService.findAll();
		model.addAttribute("offices", office);
		return new ModelAndView("forward:/admin/employees", model);
	}

	public void createEmployeeOffice(User user, PostOffice office) {

		EmployeeOffice existingEmployeeOffice = eoService.findByUserId(user.getUserId());
		
		if (existingEmployeeOffice != null) {
			eoService.deleteByUserId(user.getUserId());
		}
		
		EmployeeOffice employeeOffice = EmployeeOffice.builder()
				.user(user)
				.office(office)
				.status("Active")
				.build();
		eoService.save(employeeOffice);
	}

	@GetMapping("delete/{id}")
	public ModelAndView delet(ModelMap model, @PathVariable("id") Integer id) {
		try {
			eoService.deleteByUserId(id);
			userService.deleteById(id);
			model.addAttribute("message", "Xóa thành công!");
		} catch (Exception e) {
			model.addAttribute("message", "Không thể xóa vì nhân viên có liên quan đến nhiều bản ghi khác!");
		}
		return new ModelAndView("forward:/admin/employees", model);
	}
	
	@RequestMapping("/detail/{id}")
	public String detail(ModelMap model, @PathVariable("id") Integer userId) {
		User user = userService.getById(userId);
		model.addAttribute("user", user);
		return "admin/employees/detail";
	}
}
