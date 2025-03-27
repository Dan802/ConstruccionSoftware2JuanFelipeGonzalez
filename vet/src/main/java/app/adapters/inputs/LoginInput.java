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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@Getter
@Setter
@NoArgsConstructor
public class LoginInput implements InputPort{

	@Autowired 
	private UserValidator userValidator;
	
	@Autowired
	private AdminInput adminInput;
	
	@Autowired
	private LoginRepository loginRepository;

  @Override
  public void menu() throws Exception {
    System.out.println("Ingrese la opcion que desea:\n 1. iniciar sesion \n 2. salir");
		//String option = Utils.getReader().nextLine();
		String option = "1"; //Todo Descomentar linea de arriba
		
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
			System.out.println("\nIngrese su usuario");
			// Todo descomentar linea de abajo
			// String userName = userValidator.userNameValidator(Utils.getReader().nextLine());
			System.out.println("Usuario admin ingresado");
			String userName = "admin";
			//System.out.println("\nIngrese su contraseña");
			//String password = userValidator.passwordValidator(Utils.getReader().nextLine());

			// Buscar por username y crear el objeto 
			LoginEntity loginEntity = loginRepository.findByUserName(userName);

			if(loginEntity == null) {
				System.out.println("El usuario no existe, intentelo de nuevo");
				return;
			}

			// Todo añadir validación de la contraseña
			System.out.println("\nUsted ha ingresado correctamente");

			switch (loginEntity.getPersonId().getRole()) {
				case "Administrador": {
					adminInput.menu();
					return;
				}
				case "Vendedor": {
					return;
				}
				case "Veterinario": {
					return;
				}
				default: {
					System.out.println("Ha habido un error, comunícate con soporte.");
					System.out.println("Código error 406.");
					// Error 406: No hay rol o no coincide con ninguno :( 
					return;
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
