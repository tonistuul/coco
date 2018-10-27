package ee.netgroup.coco.controller;

import ee.netgroup.coco.model.LoginRequest;
import ee.netgroup.coco.model.PollRequest;
import ee.netgroup.coco.model.SystemUser;
import ee.netgroup.coco.model.UserIdentity;
import ee.netgroup.coco.service.SmartId;
import ee.netgroup.coco.service.SmartIdService;
import ee.netgroup.coco.service.SystemUserService;
import ee.sk.smartid.AuthenticationIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.cert.CertificateException;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Controller
public class LoginController {

  private final SystemUserService systemUserService;
  private final SmartIdService smartIdService;

  @Autowired
  public LoginController(SystemUserService systemUserService,
                         SmartIdService smartIdService) {
    this.systemUserService = systemUserService;
    this.smartIdService = smartIdService;
  }

  @CrossOrigin
  @PostMapping(path = "login", produces = {APPLICATION_JSON_VALUE})
  public ResponseEntity<SystemUser> login(
    @RequestBody LoginRequest loginRequest
  ) {
    SystemUser systemUser = systemUserService.authenticate(loginRequest.getIdentityCode(), loginRequest.getPassword());
    if(systemUser != null){
      return ResponseEntity.ok().body(systemUser);
    }
    return new ResponseEntity<>(FORBIDDEN);
  }


  @CrossOrigin
  @PostMapping(path = "smartid", produces = {APPLICATION_JSON_VALUE})
  public ResponseEntity<SmartId> loginSmartId(
    @RequestBody(required = false) UserIdentity userIdentity
  ) throws CertificateException, InterruptedException {
    SmartId smartId = smartIdService.initAuth(userIdentity.getIdentityCode());
    return ResponseEntity.accepted().body(smartId);
  }

  @CrossOrigin
  @PostMapping(path = "poll", produces = {APPLICATION_JSON_VALUE})
  public ResponseEntity<AuthenticationIdentity> pollSmartId(
    @RequestBody(required = false) PollRequest pollRequest
  ) throws CertificateException, InterruptedException {
    AuthenticationIdentity authenticationIdentity = smartIdService.validate(pollRequest.getSessionId());
    return ResponseEntity.accepted().body(authenticationIdentity);
  }
}
