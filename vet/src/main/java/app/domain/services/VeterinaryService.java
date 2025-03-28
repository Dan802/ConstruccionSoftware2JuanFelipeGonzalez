package app.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.domain.models.Person;
import app.ports.PersonPort;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Service
public class VeterinaryService {

  @Autowired
  public PersonPort personPort;

  public void registerPetOwner(Person person) throws Exception{
    if(personPort.existPerson(person.getDocument())){
      throw new Exception("Ya existe una persona con esa cedula");
    }
    personPort.savePerson(person);
  }

  public Person savePetOwner(Person petOwner) {
    Person person = personPort.savePerson(petOwner);
    return person;
  }
}
