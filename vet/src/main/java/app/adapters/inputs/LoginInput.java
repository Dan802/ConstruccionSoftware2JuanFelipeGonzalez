package app.adapters.inputs;

import app.adapters.login.LoginAdapter;
import app.adapters.login.repository.LoginRepository;
import app.domain.models.Login;
import app.adapters.inputs.utils.UserValidator;
import app.adapters.inputs.utils.Utils;

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
	private VetInput vetInput;
	@Autowired
	private SellerInput sellerInput;
	@Autowired
	private LoginRepository loginRepository;
	@Autowired
	private LoginAdapter loginAdapter;

  @Override
	public void menu() throws Exception {
    boolean controlVble = true;
    do {
      controlVble = menu2();
    } while (controlVble);
  }

	private boolean menu2() {
		System.out.println("\nIngrese la opción que desea:\n 1. Iniciar sesion \n 2. Salir");
		String option = Utils.getReader().nextLine();
		
		switch (option) {
			case "1": {
				this.login();
				return true;
			}
			case "2": {
				final String BARBIE = """
						\nGracias a todos por venir.
						Mil besos, mil besos, mil besos, mil besitos, besos, mil besos. 
						Y porfa... depositen todas sus envolturas y vasos de refresco en el depósito de basura más cercano.
						Gracias, vuelvan pronto, mil besitos.
						Fue un placer.
						""";
				System.out.println(BARBIE);
				return false;
			}
			default: {
				System.out.println("Ha elegido una opción invalida, no sea pendejo :)");
				return true;
			}
		}
	}

  private void login() {
		try {
			System.out.println("\nIngrese su usuario");
			String userName = userValidator.userNameValidator(Utils.getReader().nextLine());
			System.out.println("Usuario " + userName + " ingresado");
			
			System.out.println("\nIngrese su contraseña");
			String password = userValidator.passwordValidator(Utils.getReader().nextLine());
			System.out.println("Contraseña *** ingresada");

			Login login = loginAdapter.findByUsername(userName);
			if(!verifyUser(password, login)) return;

			switch (login.getPersonId().getRole()) {
				case "Administrador": {
					adminInput.menu();
					return;
				}
				case "Vendedor": {
					sellerInput.menu();
					return;
				}
				case "Veterinario": {
					vetInput.menu(login);
					return;
				}
				default: {
					System.out.println("Ha habido un error, comunícate con soporte.");
					System.out.println("Error: El usuario no tiene rol o no coincide con ninguno :( ");
					return;
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private boolean verifyUser(String password, Login loginEntity) throws Exception{
		if(loginEntity == null) {
			System.out.println("\nEl usuario no existe, intentelo de nuevo");
			return false;
		}
		if(!password.equals(loginEntity.getPassword())){
			System.out.println("\nLa contraseña no es correcta");
			return false;
		}
		System.out.println("\nUsted ha ingresado correctamente");
		return true;
	}
}
