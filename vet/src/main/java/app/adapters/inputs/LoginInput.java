package app.adapters.inputs;

import app.adapters.login.entity.LoginEntity;
import app.adapters.login.repository.LoginRepository;
import app.adapters.inputs.utils.UserValidator;
import app.adapters.inputs.utils.Utils;
import app.adapters.person.entity.PersonEntity;
import app.domain.models.Login;
import app.domain.models.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.ports.InputPort;

@Component
public class LoginInput implements InputPort{

	@Autowired // Todo Por que se debe conectar así? 
	private UserValidator userValidator;
	
	private AdminInput adminInput;
	
	@Autowired
	private LoginRepository loginRepository;

  @Override
  public void menu() throws Exception {
    System.out.println("Ingrese la opcion que desea:/n 1. iniciar sesion /n 2. salir");
		String option = Utils.getReader().nextLine();
		
		switch (option) {
			case "1": {
				this.login();
			}
			case "2": {
				System.out.println("Cyao");
				return;
			}
			default: {
				System.out.println("Ha elegido una opción invalida, se detiene la ejecucion.");
				return;
			}
		}
  }

  private void login() {
		try {
			System.out.println("Ingrese su usuario");
			String userName = userValidator.userNameValidator(Utils.getReader().nextLine());
			//System.out.println("Ingrese su contraseña");
			//String password = userValidator.passwordValidator(Utils.getReader().nextLine());

			// Buscar por username y crear el objeto 
			LoginEntity loginEntity = loginRepository.findByUserName(userName);
			System.out.println();
			System.out.println("Username: " + loginEntity.getUserName());
			System.out.println("Password: " +loginEntity.getPassword());
			System.out.println("Name: " +loginEntity.getDocument().getName());
			System.out.println("Role: " +loginEntity.getDocument().getRole());
			System.out.println("Age: " +loginEntity.getDocument().getAge());
			System.out.println("Document: " +loginEntity.getDocument().getDocument());

			/*switch (person.getRole()) {
				case "Administrador": {
					adminInput.menu();
					return;
				}
				case "Vendedor": {
					return;
				}
				case "Admin": {
					return;
				}
				case "Veterinario": {
					return;
				}
				default: {
					System.out.println("Ha habido un error, comunícate con soporte.");
					System.out.println("Código error 406.");
					// Error 406: No hay rol o no coinciden :( 
					return;
				}
			}*/
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
}
