package app.ports;

import java.util.List;

import app.domain.models.MedicalRecord;
import app.domain.models.Order;
import app.domain.models.Person;
import app.domain.models.Pet;

public interface PersonPort {
  public boolean existPerson(long document);
  public Person savePerson(Person person);
  public Person findByDocument(Long document);
}