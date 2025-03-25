package app.adapters.login;

import app.adapters.login.entity.LoginEntity;
import app.adapters.login.repository.LoginRepository;
import app.adapters.person.entity.PersonEntity;
import app.domain.models.Person;
import app.ports.LoginPort;

public class LoginAdapter implements LoginPort{

	private LoginRepository loginRepository;

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

}
