package app.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.adapters.person.PersonAdapter;
import app.domain.models.Person;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Service
public class VeterinaryService {

  @Autowired
  public PersonAdapter personAdapter;

  public void registerPetOwner(Person person) throws Exception{
    if(personAdapter.existPerson(person.getDocument())){
      throw new Exception("Ya existe una persona con esa cedula");
    }
    personAdapter.savePerson(person);
  }

  public Person savePetOwner(Person petOwner) {
    Person person = personAdapter.savePerson(petOwner);
    return person;
  }
}
