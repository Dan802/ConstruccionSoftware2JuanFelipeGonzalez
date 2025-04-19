package app.ports;

import app.domain.models.Login;

public interface LoginPort {
  public Login findByUsername(String username);
  public void save(Login login);
}
