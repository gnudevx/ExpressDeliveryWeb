package vn.iostar.controllers.admin;

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

import jakarta.validation.Valid;
import vn.iostar.entity.PaymentMethod;
import vn.iostar.entity.PostOffice;
import vn.iostar.model.PaymentMethodModel;
import vn.iostar.model.PostOfficeModel;
import vn.iostar.service.IPaymentMethodService;
import vn.iostar.service.IPostOfficeService;

@Controller
@RequestMapping("admin/payments")
public class PaymentMethodController {
	@Autowired(required = true)
	IPaymentMethodService paymentService;

	@RequestMapping("")
	public String list(ModelMap model, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo) {
		Page<PaymentMethod> list = paymentService.getAll(pageNo);
		model.addAttribute("payments", list);
		model.addAttribute("totalPage", list.getTotalPages() > 0 ? list.getTotalPages() : 1);
		model.addAttribute("currentPage", pageNo);
		if (list.isEmpty()) {
			model.addAttribute("message", "Không có phương thức thanh toán nào để hiển thị!");
		}
		return "admin/payments/list";
	}

	@RequestMapping("/add")
	public String add(ModelMap model) {
		PaymentMethodModel paymentModel = new PaymentMethodModel();
		paymentModel.setIsEdit(false);
		model.addAttribute("payment", paymentModel);
		return "admin/payments/addOrEdit";
	}

	@GetMapping("edit/{id}")
	public ModelAndView edit(ModelMap model, @PathVariable("id") Integer paymentId) {
		Optional<PaymentMethod> payment = paymentService.findById(paymentId);
		PaymentMethodModel paymentModel = new PaymentMethodModel();
		if (payment.isPresent()) {
			PaymentMethod entity = payment.get();
			BeanUtils.copyProperties(entity, paymentModel);
			paymentModel.setIsEdit(true);
			model.addAttribute("payment", paymentModel);
			return new ModelAndView("admin/payments/addOrEdit", model);
		}
		model.addAttribute("message", "Phương thức thanh toán không tồn tại!");
		return new ModelAndView("forward:/admin/payments", model);
	}

	@PostMapping("saveOrUpdate")
	public ModelAndView saveOrUpdate(ModelMap model, @Valid @ModelAttribute("payment") PaymentMethodModel paymentModel,
			BindingResult result) {
		if (result.hasErrors()) {
			return new ModelAndView("admin/payments/addOrEdit");
		}
		PaymentMethod entity = new PaymentMethod();
		// copy từ Model sang Entity
		BeanUtils.copyProperties(paymentModel, entity);
		if (entity.getStatus() == null) {
			entity.setStatus(false);
		}
		// gọi hàm save trong service
		paymentService.save(entity);
		// đưa thông báo về cho biến message
		String message = "";
		if (paymentModel.getIsEdit() == true) {
			message = "Chỉnh sửa thành công!";
		} else {
			message = "Thêm phương thức mới thành công!";
		}
		model.addAttribute("message", message);
		// redirect về URL controller
		return new ModelAndView("forward:/admin/payments", model);
	}

	@GetMapping("delete/{id}")
	public ModelAndView delet(ModelMap model, @PathVariable("id") Integer paymentId) {
		try {
			paymentService.deleteById(paymentId);
			model.addAttribute("message", "Xóa thành công!");
		} catch (Exception e) {
			model.addAttribute("message", "Không thể xóa vì phương thức có liên quan đến nhiều bản ghi khác!");
		}
		return new ModelAndView("forward:/admin/payments", model);
	}
}
