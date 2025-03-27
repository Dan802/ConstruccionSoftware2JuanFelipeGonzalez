package app.ports;

import app.domain.models.Person;

public interface PersonPort {
  public boolean existPerson(long document);
  public Person savePerson(Person person);
  public Person findByDocument(Long document);
}
