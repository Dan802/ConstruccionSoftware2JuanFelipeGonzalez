package app.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.Exceptions.BusinessException;
import app.Exceptions.NotFoundException;
import app.adapters.login.LoginAdapter;
import app.domain.models.Login;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Service
public class LoginService {

    @Autowired
	private LoginAdapter loginAdapter;
  
  public Login login(String userName, String password) throws Exception{
    Login login = loginAdapter.findByUsername(userName);
    verifyUser(password, login);

    return login;
    }
  
  private void verifyUser(String password, Login login) throws Exception{
		if(login == null) {
			throw new NotFoundException("\nThe user does not exist, try again");
		}
		
    if(!password.equals(login.getPassword())){
			throw new BusinessException("\nThe password is not correct");
		}
	}

	public void verifyVeterinary(String userName, String password) throws Exception{
		Login login = loginAdapter.findByUsername(userName);
		if(login == null) {
			throw new NotFoundException("\nThe user does not exist, try again");
		}
	}
}
