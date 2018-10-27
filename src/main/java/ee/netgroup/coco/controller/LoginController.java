package ee.netgroup.coco.controller;

import ee.netgroup.coco.service.SystemUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Controller
@AllArgsConstructor
public class LoginController {

  private final SystemUserService systemUserService;

  @GetMapping(path = "login", produces = {APPLICATION_JSON_VALUE})
  public ResponseEntity login(
    @RequestParam(required = false) String username,
    @RequestParam(required = false) String password
  ) {
    boolean authenticated = systemUserService.authenticate(username, password);
    return authenticated ? new ResponseEntity<>(OK) : new ResponseEntity<>(FORBIDDEN);
  }
}
