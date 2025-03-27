package app.ports;

import app.domain.models.Login;
import app.domain.models.Person;

public interface LoginPort {
   // Se debe buscar en la tabla de Login
  public Person findByUsername(String username);
  public void save(Login login);
}
