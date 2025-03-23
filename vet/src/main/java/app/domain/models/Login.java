package app.domain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Login {
  private long loginId;
  private Person personId;
  private String userName;
  private String password;
  
  public Login(long loginId, Person personId, String userName, String password) {
    this.loginId = loginId;
    this.personId = personId;
    this.userName = userName;
    this.password = password;
  }
}
