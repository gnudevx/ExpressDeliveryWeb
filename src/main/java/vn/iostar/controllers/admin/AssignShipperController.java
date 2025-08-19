package vn.iostar.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.iostar.entity.Parcel;
import vn.iostar.entity.User;
import vn.iostar.service_M.IParcelService_M;
import vn.iostar.service_M.IUserService_M;

import java.util.List;

@Controller
@RequestMapping("/admin/assign-shipper")
public class AssignShipperController {

    @Autowired
    private IParcelService_M parcelService;

    @Autowired
    private IUserService_M userService;

    // Hiển thị danh sách các đơn chưa có shipper
    @GetMapping("")
    public String getUnassignedParcels(Model model) {
        // Lấy danh sách các đơn chưa được gán shipper
        List<Parcel> unassignedParcels = parcelService.getUnassignedParcels();
        model.addAttribute("parcels", unassignedParcels);

        // Lấy danh sách tất cả shipper hiện có
        List<User> shippers = userService.findShippers();
        model.addAttribute("shippers", shippers);

        return "admin/management/shippingManagement/listUnassign"; // Tên template hiển thị danh sách
    }

    // Xử lý phân công shipper cho đơn hàng
    @PostMapping("/{parcelId}/assign")
    public String assignShipperToParcel(@PathVariable Integer parcelId, @RequestParam Integer shipperId) {
        // Gọi service để gán shipper cho parcel
        parcelService.assignShipperToParcel(parcelId, shipperId);

        // Chuyển hướng lại trang danh sách sau khi phân công thành công
        return "admin/management/shippingManagement/listUnassign";
    }
}
