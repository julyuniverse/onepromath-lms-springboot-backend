package com.onepromath.lms;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 개발 단계에서 front end(react)와 back end(spring boot)를 분리 개발하면서 필요한 cors 설정
// 운영 단계에 적용할 땐 apache에서 front end 주소와 back end 주소를 통일시키기 때문에 cors error가 발생되지 않는다. 그러므로 아래 설정은 필요 없어지고 되고 무시하여도 좋다.
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry corsRegistry) {
        corsRegistry.addMapping("/**")
                .allowedOrigins("http://localhost:3000");
    }
}
