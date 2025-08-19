package vn.iostar.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class ConfigUploadFile implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        Path uploadFileInDir = Paths.get("./update-avatar");
        String uploadPath = uploadFileInDir.toFile().getAbsolutePath();
        System.out.println("Upload path: " + uploadPath);
        registry.addResourceHandler("/update-avatar/**")
                .addResourceLocations("file:/" + uploadPath + "/");
    }
}
