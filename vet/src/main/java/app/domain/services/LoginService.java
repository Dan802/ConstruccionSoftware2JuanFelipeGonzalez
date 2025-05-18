package app.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.Exceptions.NotFoundException;
import app.adapters.inputs.AdminInput;
import app.adapters.inputs.SellerInput;
import app.adapters.inputs.VetInput;
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
  @Autowired
	private AdminInput adminInput;
	@Autowired
	private VetInput vetInput;
	@Autowired
	private SellerInput sellerInput;
  
  public void login(String userName, String password) throws Exception{
    Login login = loginAdapter.findByUsername(userName);
    verifyUser(password, login);

    // switch (login.getPersonId().getRole()) {
    //   case "Administrador": {
    //     adminInput.menu();
    //     break;
    //   }
    //   case "Vendedor": {
    //     sellerInput.menu();
    //     break;
    //   }
    //   case "Veterinario": {
    //     vetInput.menu(login);
    //     break;
    //   }
    //   default: {
    //     System.out.println("Ha habido un error, comunícate con soporte.");
    //     System.out.println("Error: El usuario no tiene rol o no coincide con ninguno :( ");
    //     break;
    //   }
    // }
  }
  
  private void verifyUser(String password, Login login) throws Exception{
		if(login == null) {
			throw new NotFoundException("\nEl usuario no existe, intentelo de nuevo");
		}
		
    if(!password.equals(login.getPassword())){
			throw new Exception("\nLa contraseña no es correcta");
		}
		
    System.out.println("\nUsted ha ingresado correctamente");
	}
}
