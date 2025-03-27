package app.domain.services;

import org.springframework.beans.factory.annotation.Autowired;

import app.domain.models.Person;
import app.ports.LoginPort;

public class LoginService {
  
  @Autowired
	private LoginPort loginPort;

	public Person login(String username) throws Exception {
		Person userValidate= loginPort.findByUsername(username);
		
    if(userValidate== null) {
			throw new Exception("Usuario o contraseña invalido");
		}
		// if(userValidate.getPassword().equals(userValidate.getPassword())) {
		// 	throw new Exception("Usuario o contraseña invalido");
		// }
		return userValidate;
	}
}
