package ee.netgroup.coco;

import ee.netgroup.coco.model.SystemUser;
import ee.netgroup.coco.repository.SystemUserRepository;
import ee.netgroup.coco.service.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static org.springframework.http.HttpStatus.UNSUPPORTED_MEDIA_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Controller
public class CocoLoginController {

  private final SystemUserService systemUserService;

  @Autowired
  public CocoLoginController(SystemUserService systemUserService) {
    this.systemUserService = systemUserService;
  }

  @GetMapping(path = "login", produces = {APPLICATION_JSON_VALUE})
  public ResponseEntity login(
    @RequestParam(required = false) String username,
    @RequestParam(required = false) String password
  ) {
    boolean authenticated = systemUserService.authenticate("John", "Wick");
    return new ResponseEntity<>(UNSUPPORTED_MEDIA_TYPE);
  }
}
