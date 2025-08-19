package vn.iostar.controllers.User;


import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.iostar.entity.Role;
import vn.iostar.entity.User;
import vn.iostar.service.User.CreateUsersServiceImpl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

//@RestController
@Slf4j
@Controller
@RequestMapping("/account")
public class Register_Login_User {
    @Autowired
    HttpSession session;
    @Autowired
    CreateUsersServiceImpl userService;
    @GetMapping("/register")
    public String Register(Model model){
        User user = new User();
        model.addAttribute("User", user);
//        return "user/create_user";
        return "security/Register";
    }
    @PostMapping("/save")
    public String save( @ModelAttribute(name = "User") User user,
                        @RequestParam("images") MultipartFile multipartFile,
                        @RequestParam("option") int selectedOption,
                        RedirectAttributes redirectAttributes
                        ) throws IOException {
        //create file name
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        System.out.println(fileName);
        user.setImagePath(fileName);
        System.out.println((user.getAvatarImagePath()));
        user.setRole(Role.builder().roleId(selectedOption).build());
        User saveUser = userService.create(user);
        String uploadDir = "./update-avatar/" +  saveUser.getUserId();

        // save path in dir
        Path uploadPath = Paths.get(uploadDir);
        if(!Files.exists(uploadPath)){
            Files.createDirectories(uploadPath);
        }

        try(InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath,StandardCopyOption.REPLACE_EXISTING);
            System.out.println(filePath.toFile().getAbsolutePath());
        } catch (IOException e) {
            throw new IOException("not success save file" + fileName);
        }
        redirectAttributes.addFlashAttribute("alert", "Mật khẩu đã được tạo, vui lòng nhập mật khẩu đã cung cấp tại gmail!");
        return "redirect:/account/login_account";
    }
    @GetMapping("/login_account")
    public String showLoginForm(Model model) {
        return "security/Login"; // Trả về tên template Thymeleaf (login.html)
    }
    @PostMapping("/login")
    public String loginUser(@RequestParam String email,
                            @RequestParam String password,
                            HttpServletRequest request, HttpServletResponse response,
                            RedirectAttributes redirectAttributes) {
        String token = userService.loginUser(email, password);
        System.out.println(token);
        if (token == null) {
            redirectAttributes.addFlashAttribute("alert", "Sai mật khẩu. Vui lòng thử lại!");
            return "redirect:/account/login_account"; // Quay lại form đăng nhập với thông báo lỗi
        }else {
            User user = userService.getUser(email);
            Cookie jwtCookie = new Cookie("jwt_token", token);
            jwtCookie.setHttpOnly(true);
            jwtCookie.setPath("/");
            response.addCookie(jwtCookie);
            session.setAttribute("user", user);
            redirectAttributes.addFlashAttribute("user", user);
            if (user.getRole().getName().equalsIgnoreCase("USER")) {
                return "redirect:/home/home_user"; // Chuyển hướng đến trang home nếu role là USER
            } else if (user.getRole().getName().equalsIgnoreCase("SHIPPER")) {
                return "redirect:/shipper/listPacel"; // Chuyển hướng đến trang shipper nếu role là SHIPPER
            } else {
                return "redirect:/admin/dashboard"; // Mặc định chuyển hướng đến admin
            }

        }

    }
    @GetMapping("/logout")
    public String Logout(HttpSession session) {
        session.invalidate();
        return "security/Login";
    }
}
