package app.ports;

import app.domain.models.Login;

public interface LoginPort {
   // Se debe buscar en la tabla de Login
  public Login findByUsername(String username);
  public void save(Login login);
}
