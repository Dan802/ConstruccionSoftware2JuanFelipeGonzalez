package app.adapters.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.adapters.person.entity.PersonEntity;
import app.adapters.person.repository.PersonRepository;
import app.domain.models.Person;
import app.ports.PersonPort;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Service
public class PersonAdapter implements PersonPort {

  @Autowired
  private PersonRepository personRepository;
  
  @Override
  public boolean existPerson(long document) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'existPerson'");
  }

  @Override
  public void savePerson(Person person) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'savePerson'");
  }
}
