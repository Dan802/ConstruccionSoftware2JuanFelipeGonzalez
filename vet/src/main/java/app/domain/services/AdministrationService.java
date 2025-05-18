package app.domain.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.Exceptions.BusinessException;
import app.Exceptions.NotFoundException;

import app.adapters.login.LoginAdapter;
import app.adapters.person.PersonAdapter;
import app.domain.models.Login;
import app.domain.models.Person;
import app.ports.LoginPort;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
  
@Setter
@Getter
@NoArgsConstructor
@Service
public class AdministrationService {
  
  @Autowired
  private PersonAdapter personAdapter;
  @Autowired
  private LoginAdapter loginAdapter;
  @Autowired
  private LoginPort loginPort;

  public void registerPerson(Person newPerson, Login login) throws Exception {
    if(personAdapter.existPerson(newPerson.getDocument())){
      throw new BusinessException("Ya existe una persona con esa cedula");
    }

    if (loginAdapter.findByUsername(login.getUserName()) != null){
      throw new BusinessException("Ya existe ese username registrado");
    }

    Person person = personAdapter.save(newPerson);
    login.setPersonId(person);
    loginAdapter.save(login);
  }

  public void createPerson(long document, String name, int age, String userName, String password, String role) throws Exception {
    Person newPerson = new Person();
    newPerson.setDocument(document);
    newPerson.setName(name);
    newPerson.setAge(age);
    newPerson.setRole(role);

    Login newLoginInfo = new Login();
    newLoginInfo.setUserName(userName);
    newLoginInfo.setPassword(password);

    // Guardamos la persona
    // Nota: Este metodo no esta partido en dos porque así se guarda en la bd 
    // si y solo si el documento y username no estan duplicados
    registerPerson(newPerson, newLoginInfo);
    System.out.println("\nPersona guardada correctamente");
  }

  public List<Login> getUsers() throws Exception {
		List<Login> users = loginPort.getAll();
		if(users.isEmpty()) {
			throw new NotFoundException("No se encontraron usuarios"); 
		}
		return users;
	}
}