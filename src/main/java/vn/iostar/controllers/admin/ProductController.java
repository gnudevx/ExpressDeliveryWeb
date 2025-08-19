package vn.iostar.controllers.admin;

import java.io.IOException;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import jakarta.validation.Valid;
import vn.iostar.entity.Product;
import vn.iostar.model.ProductModel;
import vn.iostar.service.CloudinaryService;
import vn.iostar.service.admin.IProductService;

@Controller
@RequestMapping("admin/products")
public class ProductController {
    @Autowired
    IProductService productService;
    @Autowired
    private CloudinaryService cloudinaryService;

    @RequestMapping("")
    public String list(ModelMap model, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo) {
        Page<Product> list = productService.getAll(pageNo);
        model.addAttribute("products", list);
        model.addAttribute("totalPage", list.getTotalPages() > 0 ? list.getTotalPages() : 1);
        model.addAttribute("currentPage", pageNo);
        if (list.isEmpty()) {
            model.addAttribute("message", "Không có sản phẩm nào để hiển thị!");
        }
        return "admin/products/list";
    }

    @RequestMapping("/add")
    public String add(ModelMap model) {
        ProductModel productModel = new ProductModel();
        productModel.setIsEdit(false);
        model.addAttribute("product", productModel);
        return "admin/products/addOrEdit";
    }

    @GetMapping("edit/{id}")
    public ModelAndView edit(ModelMap model, @PathVariable("id") Integer productId) {
        Optional<Product> product = productService.findById(productId);
        ProductModel productModel = new ProductModel();
        if (product.isPresent()) {
            Product entity = product.get();
            BeanUtils.copyProperties(entity, productModel);
            productModel.setIsEdit(true);
            model.addAttribute("product", productModel);
            return new ModelAndView("admin/products/addOrEdit", model);
        }
        model.addAttribute("message", "Sản phẩm không tồn tại!");
        return new ModelAndView("forward:/admin/products", model);
    }

    @PostMapping("saveOrUpdate")
    public ModelAndView saveOrUpdate(ModelMap model, @Valid @ModelAttribute("product") ProductModel productModel,
                                     BindingResult result, @RequestParam(value = "image", required = false) MultipartFile image) {
//        if (result.hasErrors()) {
//        	 result.getAllErrors().forEach(error -> {
//        	        System.out.println("Error: " + error.getDefaultMessage());
//        	    });
//            return new ModelAndView("admin/products/addOrEdit");
//        }

        String imageUrl =null;
        try {
            // Kiểm tra nếu ảnh không rỗng và không phải null
            if (image != null && !image.isEmpty()) {
            	 imageUrl = cloudinaryService.uploadImage(image.getBytes());
            } else {
                model.addAttribute("message", "Vui lòng chọn ảnh để tải lên.");
                return new ModelAndView("admin/products/addOrEdit", model);
            }
        } catch (IOException e) {
            // Xử lý lỗi khi có sự cố với việc đọc byte từ file
            e.printStackTrace();
            model.addAttribute("message", "Lỗi khi tải ảnh lên, vui lòng thử lại.");
            return new ModelAndView("admin/products/addOrEdit", model);
        }
        Product entity = new Product();
        // Copy các thuộc tính từ ProductModel sang Product entity
        BeanUtils.copyProperties(productModel, entity);
        if (imageUrl != null) {
            entity.setImage(imageUrl);  // Cập nhật ảnh cho sản phẩm
        }

        // Lưu hoặc cập nhật sản phẩm
        productService.save(entity);

        // Thông báo thành công
        String message = "";
		if (productModel.getIsEdit() == true) {
			message = "Chỉnh sửa thành công!";
		} else {
			message = "Thêm thành công!";
		}
		model.addAttribute("message", message);
		// redirect về URL controller

        // Redirect về trang danh sách sản phẩm
        return new ModelAndView("forward:/admin/products", model);
    }


//    @GetMapping("delete/{id}")
//    public ModelAndView delete(ModelMap model, @PathVariable("id") Integer productId) {
//        try {
//            productService.deleteById(productId);
//            model.addAttribute("message", "Xóa sản phẩm thành công!");
//        } catch (Exception e) {
//            model.addAttribute("message", "Không thể xóa vì sản phẩm có liên quan đến nhiều bản ghi khác!");
//        }
//        return new ModelAndView("forward:/admin/products", model);
//    }
}
