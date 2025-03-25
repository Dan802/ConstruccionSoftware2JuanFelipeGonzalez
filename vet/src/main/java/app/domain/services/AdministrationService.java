package app.domain.services;

import app.domain.models.Person;
import app.ports.PersonPort;

public class AdministrationService implements PersonPort{
  
  public void setUserAndPassword(){

  }

  @Override
  public boolean existPerson(long document) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'existPerson'");
  }

  @Override
  public void savePerson(Person person) {
    // SOLO VENDEDORES Y VETRINARIOS
  }

}