package ee.netgroup.coco.controller;

import ee.netgroup.coco.model.*;
import ee.netgroup.coco.service.SmartId;
import ee.netgroup.coco.service.SmartIdService;
import ee.netgroup.coco.service.AuthenticationService;
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

  private final AuthenticationService authenticationService;
  private final SmartIdService smartIdService;

  @Autowired
  public LoginController(AuthenticationService authenticationService,
                         SmartIdService smartIdService) {
    this.authenticationService = authenticationService;
    this.smartIdService = smartIdService;
  }

  @CrossOrigin
  @PostMapping(path = "login", produces = {APPLICATION_JSON_VALUE})
  public ResponseEntity<Person> login(
    @RequestBody LoginRequest loginRequest
  ) {
    Person person = authenticationService.authenticate(loginRequest.getIdentityCode());
    return person != null ? ResponseEntity.ok().body(person) : new ResponseEntity<>(FORBIDDEN);
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
