package vn.iostar.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CloudinaryService {

    @Autowired
    Cloudinary cloudinary;

    public String uploadImage(byte[] imageBytes) {
        // Ví dụ: Phương thức này sử dụng Cloudinary API để upload ảnh
        // Đảm bảo bạn có cấu hình đúng và trả về URL ảnh hợp lệ
        try {
            Map uploadResult = cloudinary.uploader().upload(imageBytes, ObjectUtils.emptyMap());
            return (String) uploadResult.get("url");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
