package app.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
  public PersonPort personPort;

  @Autowired
  public LoginPort loginPort;

  public Person registerPerson(Person newPerson) throws Exception{
    if(personPort.existPerson(newPerson.getDocument())){
      throw new Exception("Ya existe una persona con esa cedula");
    }
    
    return personPort.savePerson(newPerson);
  }

  public void registerLogin(Login login) throws Exception {
    if (loginPort.findByUsername(login.getUserName()) != null){
      throw new Exception("Ya existe ese username registrado");
      //! Registro corrompido, borrar manualmente :(
    }

    loginPort.save(login);
  }
}