package vn.iostar.controllers.admin;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import vn.iostar.entity.Parcel;
import vn.iostar.entity.ParcelType;
import vn.iostar.entity.PaymentMethod;
import vn.iostar.entity.PostOffice;
import vn.iostar.entity.Recipient;
import vn.iostar.entity.ShippingType;
import vn.iostar.entity.User;
import vn.iostar.model.ParcelModel;
import vn.iostar.model.PostOfficeModel;
import vn.iostar.model.RecipientModel;
import vn.iostar.service.IParcelService;
import vn.iostar.service.IPaymentMethodService;
import vn.iostar.service.IPostOfficeService;
import vn.iostar.service.IRecipientService;
import vn.iostar.service.IUserService;
import vn.iostar.service_M.IParcelTypeService_M;
import vn.iostar.service_M.IShippingFeeService_M;
import vn.iostar.service_M.IShippingTypeService_M;

@Controller
@RequestMapping("admin/parcels")
public class ParcelControllers {

	@Autowired(required = true)
	IParcelService parcelService;

	@Autowired(required = true)
	IUserService userService;

	@Autowired(required = true)
	IRecipientService reService;
	
	@Autowired(required = true)
	IPaymentMethodService paymentService;
	
	@Autowired(required = true)
	IPostOfficeService officeService;
	
	@Autowired(required = true)
	IShippingTypeService_M shippingTypeService;
	
	@Autowired(required = true)
	IParcelTypeService_M parcelTypeService;
	
	@Autowired(required = true)
	IShippingFeeService_M shippingFeeService;

	// Hiển thị danh sách vận đơn với phân trang
	@RequestMapping("")
	public String list(ModelMap model, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo) {
		Page<Parcel> list = parcelService.getAll(pageNo);
		model.addAttribute("parcels", list);
		model.addAttribute("totalPage", list.getTotalPages() > 0 ? list.getTotalPages() : 1);
		model.addAttribute("currentPage", pageNo);
		if (list.isEmpty()) {
			model.addAttribute("message", "Không có vận đơn nào để hiển thị!");
		}
		return "admin/parcels/list";
	}

	// Thêm vận đơn mới
	@RequestMapping("/add")
	public String add(ModelMap model) {
		ParcelModel parcelModel = new ParcelModel();
		parcelModel.setIsEdit(false);
		model.addAttribute("parcel", parcelModel);
		return "admin/parcels/addOrEdit";
	}

	// Sửa vận đơn
	@GetMapping("edit/{id}")
	public ModelAndView edit(ModelMap model, @PathVariable("id") Integer parcelId) {
		Optional<Parcel> parcel = parcelService.findById(parcelId);
		ParcelModel parcelModel = new ParcelModel();
		if (parcel.isPresent()) {
			Parcel entity = parcel.get();
			BeanUtils.copyProperties(entity, parcelModel);
			parcelModel.setIsEdit(true);
			model.addAttribute("parcel", parcelModel);
			return new ModelAndView("admin/parcels/addOrEdit", model);
		}
		model.addAttribute("message", "Vận đơn không tồn tại!");
		return new ModelAndView("forward:/admin/parcels", model);
	}

	// Lưu hoặc cập nhật vận đơn
	@PostMapping("saveOrUpdate")
	public ModelAndView saveOrUpdate(ModelMap model, @Valid @ModelAttribute("parcel") ParcelModel parcelModel,
			BindingResult result) {
		if (result.hasErrors()) {
			return new ModelAndView("admin/parcels/addOrEdit");
		}

		Parcel entity = new Parcel();
		// Sao chép từ Model sang Entity
		BeanUtils.copyProperties(parcelModel, entity);

		// Lưu hoặc cập nhật vận đơn
		parcelService.save(entity);

		// Thông báo sau khi lưu
		String message = "";
		if (parcelModel.getIsEdit() == true) {
			message = "Chỉnh sửa vận đơn thành công!";
		} else {
			message = "Thêm vận đơn mới thành công!";
		}
		model.addAttribute("message", message);

		// Chuyển hướng về danh sách vận đơn
		return new ModelAndView("forward:/admin/parcels", model);
	}

	// Xóa vận đơn
	@GetMapping("delete/{id}")
	public ModelAndView delete(ModelMap model, @PathVariable("id") Integer parcelId) {
		try {
			parcelService.deleteById(parcelId);
			model.addAttribute("message", "Xóa vận đơn thành công!");
		} catch (Exception e) {
			model.addAttribute("message", "Không thể xóa vì vận đơn có liên quan đến các bản ghi khác!");
		}
		return new ModelAndView("forward:/admin/parcels", model);
	}

	// Thêm vận đơn mới
	@RequestMapping("/addUser")
	public String addUser() {
		return "admin/parcels/addUser";
	}

	@RequestMapping("/saveUser")
	public String saveUser(RedirectAttributes redirectAttributes, @RequestParam(name = "email") String email) {
		User user = userService.findByEmail(email);
		if (user != null) {
			redirectAttributes.addFlashAttribute("userId", user.getUserId());
			return "redirect:/admin/parcels/addRecipient";
		} else {
			redirectAttributes.addFlashAttribute("email", email);
			redirectAttributes.addFlashAttribute("message", "Không tìm thấy người dùng với email đã nhập!");
			return "redirect:/admin/parcels/addUser";
		}
	}

	@RequestMapping("/addRecipient")
	public String addRecipient(ModelMap model) {
		RecipientModel reModel = new RecipientModel();
		model.addAttribute("recipient", reModel);
		return "admin/parcels/addRecipient";
	}

	@RequestMapping("/saveRecipient")
	public ModelAndView saveRecipient(ModelMap model, @Valid @ModelAttribute("recipient") RecipientModel reModel,
			@RequestParam(name = "userId") Integer userId, BindingResult result) {
		if (result.hasErrors()) {
			model.addAttribute("userId", userId);
			return new ModelAndView("admin/parcels/addRecipient");
		}
		reModel.setUser(userService.getById(userId));
		Recipient entity = new Recipient();
		// copy từ Model sang Entity
		BeanUtils.copyProperties(reModel, entity);
		// gọi hàm save trong service
		Recipient newRe = reService.save(entity);

		model.addAttribute("recipientId", newRe.getRecipientId());
		return new ModelAndView("redirect:/admin/parcels/addParcel", model);
	}

	@RequestMapping("/addParcel")
	public String addParcel(@RequestParam(name = "recipientId") Integer recipientId, ModelMap model) {
		model.addAttribute("recipientId", recipientId);
		ParcelModel parcelModel = new ParcelModel();
		model.addAttribute("parcel", parcelModel);
		
		List<PaymentMethod> li = paymentService.findAll();
		List<PaymentMethod> list = new ArrayList<>();
		for (PaymentMethod x:li) {
			if (x.getStatus() == true) {
				list.add(x);
			}
		}
		model.addAttribute("listPayments", list);
		
		model.addAttribute("listOffice", officeService.findAll());
		
		model.addAttribute("listShippingType", shippingTypeService.findAll());
		
		return "admin/parcels/addParcel";
	}
	
	@PostMapping("saveParcel")
	public ModelAndView saveParcel(ModelMap model, 
			@RequestParam(name = "recipientId") Integer repicientId,
			@Valid @ModelAttribute("parcel") ParcelModel parcelModel,
			@RequestParam(name = "paymentId") Integer paymentId,
			@RequestParam(name = "startOfficeId") Integer startOfficeId,
			@RequestParam(name = "desOfficeId") Integer desOfficeId,
			@RequestParam(name = "shippingTypeId") Integer shippingTypeId,
			BindingResult result) {
		if (result.hasErrors()) {
			return new ModelAndView("admin/parcels/addParcel");
		}
		
		Recipient recipient = reService.getById(repicientId);
		parcelModel.setRecipient(recipient);
		
		User user = recipient.getUser();
		parcelModel.setUser(user);
		
		parcelModel.setCreateDate(LocalDateTime.now());
		
		//Thêm phương thức getById của PaymentMethod
		PaymentMethod payment = paymentService.getById(paymentId);
		parcelModel.setPaymentMethod(payment);
		
		//Thêm phương thức này luôn
		PostOffice startOffice = officeService.getById(startOfficeId);
		parcelModel.setStartOffice(startOffice);
		PostOffice desOffice = officeService.getById(desOfficeId);
		parcelModel.setDestinationOffice(desOffice);
		
		//Thêm phương thức này luôn
		ShippingType shippingType = shippingTypeService.getById(shippingTypeId);
		parcelModel.setShippingType(shippingType);
		
		parcelModel.setStatus("Cho xu li");
		
		ParcelType parcelType = parcelTypeService.findParcelType(parcelModel.getWeight());
		parcelModel.setParcelType(parcelType);
		
		parcelModel.setShippingFee(shippingFeeService.findShippingFee(parcelType.getParcelTypeId(), shippingTypeId));
		
		Parcel entity = new Parcel();
		// copy từ Model sang Entity
		BeanUtils.copyProperties(parcelModel, entity);
		
		
		// gọi hàm save trong service
		Parcel newParcel = parcelService.save(entity);
		return new ModelAndView("redirect:/admin/management/Parcel-Management/parcels");
	}
}
