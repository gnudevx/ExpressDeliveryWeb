package vn.iostar.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Configuration
public class CloudinaryConfig {
    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dawv5yr3i",
                "api_key", "177273259971468",
                "api_secret", "dwZ46Zu9SICQugaKT18KOFN4WWA"
        ));
    }
}
