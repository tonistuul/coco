package ee.netgroup.coco.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfigurer implements WebMvcConfigurer {

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
      .allowedHeaders("*")
      .allowedMethods("*")
      .allowedOrigins("*");
  }

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }
}
