package app.ports;

import app.domain.models.Person;

public interface PersonPort {
  public boolean existPerson(long document);
  public Person save(Person person);
  public Person findByDocument(Long document);
}