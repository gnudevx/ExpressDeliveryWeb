package vn.iostar.controllers.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import vn.iostar.entity.Parcel;
import vn.iostar.entity.PostOffice;
import vn.iostar.models.ParcelDTO;
import vn.iostar.repository.PostOfficeRepository_M;
import vn.iostar.service_M.IParcelService_M;
import vn.iostar.service_M.IPostOfficeService_M;

@Controller
@RequestMapping("admin/management/Parcel-Management/parcels")
public class ParcelController {
//http://localhost:8181/admin/management/AdminAccountManagement/parcels
    @Autowired
    private IParcelService_M parcelService;  // Inject service theo interface
    
    @GetMapping("")
    public String getAllParcels(Model model) {
        List<ParcelDTO> parcels = parcelService.findAll();
        List<String> statuses = parcelService.getAllStatuses(); // Lấy danh sách trạng thái
        List<String> postOffices = postOfficeService.getAllOptionAddress();
        model.addAttribute("postOffices", postOffices);
        model.addAttribute("parcels", parcels);
        model.addAttribute("statuses", statuses); // Thêm vào model
        return "admin/management/ParcelManagement/listParcel"; 
    }
    @PostMapping("updateStatus/{id}")
    public String updateParcelStatus(@PathVariable Integer id, @RequestParam String status, RedirectAttributes redirectAttributes) {
        parcelService.updateParcelStatus(id, status); // Gọi phương thức cập nhật
        redirectAttributes.addFlashAttribute("message", "Cập nhật trạng thái thành công!");
        return "redirect:/admin/management/Parcel-Management/parcels"; // Redirect về danh sách
    }
    @GetMapping("parcelDetail/{id}")
    public String getParcelDetails(@PathVariable Integer id, Model model) {
        // Lấy ParcelDTO từ service
        ParcelDTO parcelDTO = parcelService.getParcelById(id);
        
        // Nếu không tìm thấy ParcelDTO, trả về trang lỗi (404)
        if (parcelDTO == null) {
            return "error/404";
        }

        // Thêm ParcelDTO vào model để truyền đến view
        model.addAttribute("parcel", parcelDTO);

        return "admin/management/ParcelManagement/parcelDetail";  // Tên template Thymeleaf
    }
    @Autowired
    private PostOfficeRepository_M postOfficeRepository; // Đảm bảo rằng bạn đã inject repository này
    
    @Autowired
    private IPostOfficeService_M postOfficeService; 

    @GetMapping("/filter")
    public String filterParcelsByStartOffice(@RequestParam(required = false) String address, Model model) {
        List<String> postOffices = postOfficeService.getAllOptionAddress();
        model.addAttribute("postOffices", postOffices);

        List<ParcelDTO> parcels;
        if (address != null && !address.isEmpty()) {
            parcels = parcelService.findByStartOfficeAddress(address);
            model.addAttribute("selectedAddress", address); 
        } else {
            parcels = parcelService.findAll();
        }

        model.addAttribute("parcels", parcels);
        model.addAttribute("statuses", parcelService.getAllStatuses());
        
        return "admin/management/ParcelManagement/listParcel"; 
    }

}
