package ee.netgroup.coco.service;

import ee.sk.smartid.*;
import ee.sk.smartid.rest.dao.NationalIdentity;
import ee.sk.smartid.rest.dao.SessionStatus;
import org.springframework.stereotype.Service;

import java.security.cert.CertificateException;
import java.util.Base64;
import java.util.concurrent.TimeUnit;

@Service
public class SmartIdService {

  private static final String CA_CERTIFICATE = "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";
  private static final String RELYING_PARTY_UUID = "11111111-0000-0000-0000-000000000000";
  private static final String RELYING_PARTY_NAME = "DEMO POC";
  private static final String SERVER_URL = "https://sid.demo.sk.ee/smart-id-rp/v1";

  private SmartIdAuthenticationClient client;

  public SmartIdService() {
    SmartIdAuthenticationClient client = new SmartIdAuthenticationClient();
    client.setRelyingPartyUUID(RELYING_PARTY_UUID);
    client.setRelyingPartyName(RELYING_PARTY_NAME);
    client.setHostUrl(SERVER_URL);
    this.client = client;
  }

  public SmartId initAuth(String inputNationalIdentityNumber) throws CertificateException, InterruptedException {
    String inputCountry = "EE";
//    String inputNationalIdentityNumber = "10101010005";

    NationalIdentity identity = new NationalIdentity(inputCountry, inputNationalIdentityNumber);

    AuthenticationHash authenticationHash = new AuthenticationHash();
    authenticationHash.setHashInBase64("K74MSLkafRuKZ1Ooucvh2xa4Q3nz+R/hFWIShN96SPHNcem+uQ6mFMe9kkJQqp5EaoZnJeaFpl310TmlzRgNyQ==");
    authenticationHash.setHashType(HashType.SHA512);

    ExtendedAuthenticationRequestBuilder builder = client.createAuthentication();
    String sessionId = ((ExtendedAuthenticationRequestBuilder) builder
      .withNationalIdentity(identity)
      .withAuthenticationHash(authenticationHash)
      .withCertificateLevel("ADVANCED")
    ).initiateAuthentication();

    SmartId smartId = new SmartId();
    smartId.verificationCode = authenticationHash.calculateVerificationCode();
    smartId.sessionId = sessionId;

    return smartId;
  }

  public AuthenticationIdentity validate(String sessionId) throws InterruptedException, CertificateException {


    // session status short poll method
    ExtendedAuthenticationRequestBuilder builder = client.createAuthentication();
    SessionStatus sessionStatus;

    sessionStatus = builder.getSessionStatus(sessionId, TimeUnit.SECONDS, 1);
    System.out.println(sessionStatus.getState());

    if("RUNNING".equals(sessionStatus.getState())){
      return null;
    }

    SmartIdAuthenticationResponse response = builder.getAuthenticationResponse(sessionStatus);
    AuthenticationResponseValidator validator = new AuthenticationResponseValidator();
    validator.addTrustedCACertificate(Base64.getDecoder().decode(CA_CERTIFICATE));
    SmartIdAuthenticationResult result = validator.validate(response);

    return result.getAuthenticationIdentity();
  }

}
