package app.domain.services;

import app.domain.models.Person;
import app.ports.AdministrationPort;

public class AdministrationService implements AdministrationPort{
  
  public void setUserAndPassword(){

  }

  @Override
  public Person createSeller() {
    setUserAndPassword();
    return null; //Todo
  }

  @Override
  public Person createVeterinary() {
    setUserAndPassword();
    return null; //Todo
  }

  @Override
  public Person createAdmin() {
    setUserAndPassword();
    return null; //Todo
  }
}
