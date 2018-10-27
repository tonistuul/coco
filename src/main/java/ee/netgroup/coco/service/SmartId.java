package ee.netgroup.coco.service;

import ee.sk.smartid.rest.dao.SessionStatus;

public class SmartId {
  String sessionId;
  String verificationCode;

  public String getSessionId() {
    return sessionId;
  }

  public void setSessionId(String sessionId) {
    this.sessionId = sessionId;
  }

  public String getVerificationCode() {
    return verificationCode;
  }

  public void setVerificationCode(String verificationCode) {
    this.verificationCode = verificationCode;
  }
}
