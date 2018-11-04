package ee.netgroup.coco;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class CocoApplication {

  public static void main(String[] args) {
    SpringApplication.run(CocoApplication.class, args);
  }
}
