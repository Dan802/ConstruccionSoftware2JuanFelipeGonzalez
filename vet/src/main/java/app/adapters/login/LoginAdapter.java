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
  @Autowired
  private PersonAdapter personAdapter;

	@Override
	public Login findByUsername(String username) {
		LoginEntity loginEntity = loginRepository.findByUserName(username);

		if(loginEntity == null) {
      return null;
    }

   return loginAdapter(loginEntity); 
	}

  private Login loginAdapter(LoginEntity loginEntity) {
    Login login = new Login();
    login.setLoginId(loginEntity.getLoginId());
    login.setPassword(loginEntity.getPassword());
    login.setPersonId(personAdapter.findByDocument(loginEntity.getPersonId().getDocument()));
    login.setUserName(loginEntity.getUserName());
    return login;
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
