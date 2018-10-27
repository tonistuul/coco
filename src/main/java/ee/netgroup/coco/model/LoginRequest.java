package ee.netgroup.coco.model;

public class LoginRequest {
  private String identityCode;
  private String password;

  public void setIdentityCode(String identityCode) {
    this.identityCode = identityCode;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getIdentityCode() {
    return identityCode;
  }

  public String getPassword() {
    return password;
  }
}
