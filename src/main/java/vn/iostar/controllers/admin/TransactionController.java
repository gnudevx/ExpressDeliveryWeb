package vn.iostar.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vn.iostar.model.TransactionDTO;
import vn.iostar.model.TransactionDetailDTO;
import vn.iostar.service.ITransactionService;

@Controller
@RequestMapping("/admin/transaction")
public class TransactionController {
	@Autowired(required = true)
	ITransactionService transactionService;

	@RequestMapping("")
	public String list(ModelMap model, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo) {
		Page<TransactionDTO> list = transactionService.getAll(pageNo);
		model.addAttribute("transaction", list);
		model.addAttribute("totalPage", list.getTotalPages() > 0 ? list.getTotalPages() : 1);
		model.addAttribute("currentPage", pageNo);
		if (list.isEmpty()) {
			model.addAttribute("message", "Chưa có bưu cục nào để hiển thị. Vui lòng tiến hành tạo bưu cục!");
		}
		return "admin/transaction/list";
	}

	@GetMapping("/detail/{id}")
	public String detail(ModelMap model, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
			@PathVariable("id") Integer officeId) {
		Page<TransactionDetailDTO> list = transactionService.getOffice(pageNo, officeId);
		model.addAttribute("transaction", list);
		model.addAttribute("totalPage", list.getTotalPages() > 0 ? list.getTotalPages() : 1);
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("office_id", officeId);
		if (list.isEmpty()) {
			model.addAttribute("message", "Bưu cục chưa có giao dịch nào để hiển thị!");
		}
		return "admin/transaction/detail";
	}
}
