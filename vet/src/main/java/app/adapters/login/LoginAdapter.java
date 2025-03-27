package app.adapters.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.adapters.login.entity.LoginEntity;
import app.adapters.login.repository.LoginRepository;
import app.adapters.person.PersonAdapter;
import app.adapters.person.entity.PersonEntity;
import app.adapters.person.repository.PersonRepository;
import app.domain.models.Login;
import app.domain.models.Person;
import app.ports.LoginPort;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Service
public class LoginAdapter implements LoginPort{

  @Autowired
	private LoginRepository loginRepository;

  @Autowired
  private PersonRepository personRepository;

  private PersonAdapter personAdapter;

	@Override
	public Person findByUsername(String username) {
		LoginEntity personEntity = loginRepository.findByUserName(username);

		if(personEntity == null) {
      return null;
    }

		return null;
    // return personAdapter(personEntity);
	}

	private Person personAdapter(PersonEntity personEntity) {
    Person person = new Person();
    person.setDocument(personEntity.getDocument());
    person.setName(personEntity.getName());
    person.setRole(personEntity.getRole());
    person.setAge(personEntity.getAge());
    return person;
  }

  @Override
  public void save(Login login) {
    PersonEntity personEntity = new PersonEntity();
    personEntity.setAge(login.getPersonId().getAge());
    personEntity.setDocument(login.getPersonId().getDocument());
    personEntity.setName(login.getPersonId().getName());
    personEntity.setRole(login.getPersonId().getRole());

    LoginEntity loginEntity = new LoginEntity();
    loginEntity.setPersonId(personEntity);
    loginEntity.setUserName(login.getUserName());
    loginEntity.setPassword(login.getPassword());
    loginRepository.save(loginEntity);
  }
}
