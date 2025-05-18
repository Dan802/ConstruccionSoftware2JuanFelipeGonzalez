package app.ports;

import java.util.List;

import app.domain.models.Login;

public interface LoginPort {
  public Login findByUsername(String username);
  public void save(Login login);
  public List<Login> getAll();
}
