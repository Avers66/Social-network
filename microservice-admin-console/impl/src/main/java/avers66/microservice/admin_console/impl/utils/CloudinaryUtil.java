package avers66.microservice.admin_console.impl.utils;

import avers66.microservice.admin_console.impl.config.CloudinaryConfig;
import com.cloudinary.Cloudinary;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * CloudinaryConfig
 *
 * @author Georgii Vinogradov
 */

@Configuration
@RequiredArgsConstructor
public class CloudinaryUtil {

    private final CloudinaryConfig cloudinaryConfig;

    @Bean
    public Cloudinary getConfig() {
        return new Cloudinary(cloudinaryConfig.getConfig());
    }
}
