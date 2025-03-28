package app.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.adapters.login.LoginAdapter;
import app.adapters.person.PersonAdapter;
import app.domain.models.Login;
import app.domain.models.Person;
import app.ports.LoginPort;
import app.ports.PersonPort;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
  
@Setter
@Getter
@NoArgsConstructor
@Service
public class AdministrationService {
  
  @Autowired
  public PersonAdapter personAdapter;
  @Autowired
  public LoginAdapter loginAdapter;

  public void registerPerson(Person newPerson, Login login) throws Exception{
    if(personAdapter.existPerson(newPerson.getDocument())){
      throw new Exception("Ya existe una persona con esa cedula");
    }

    if (loginAdapter.findByUsername(login.getUserName()) != null){
      throw new Exception("Ya existe ese username registrado");
    }

    Person person = personAdapter.savePerson(newPerson);
    login.setPersonId(person);
    loginAdapter.save(login);
  }
}