package app.adapters.inputs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.adapters.inputs.utils.PersonValidator;
import app.adapters.inputs.utils.UserValidator;
import app.adapters.inputs.utils.Utils;
import app.domain.services.AdministrationService;
import app.ports.InputPort;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Component
public class AdminInput implements InputPort {

  private final String MENU = "\nMenu del administrador, ingrese la opción:" + 
  " \n 1. Crear vendedor." + 
  " \n 2. Crear veterinario." +
  " \n 3. Salir.";

  @Autowired
  private PersonValidator personValidator;
  @Autowired
	private UserValidator userValidator;
  @Autowired
  private AdministrationService administrationService;

  @Override
  public void menu() throws Exception {
    boolean controlVble = true;
    do {
      controlVble = menu2();
    } while (controlVble);
  }

  public boolean menu2() {
    System.out.println(MENU);
    String option = Utils.getReader().nextLine();
  
    try {
			switch (option) {
			case "1": {
        this.createPerson("Vendedor");
        return true;
			}
			case "2" :{
        this.createPerson("Veterinario");
        return true;
			}
      case "3" : {
        System.out.println("Has salido exitosamente.");
        return false;
      }
			default:
				System.out.println("Opción no valida, no sea pendejo :)");
        return true;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
      return true;
		}
  }

  public void createPerson(String role) throws Exception{
    System.out.println("\nIngrese el documento de la persona"); 
    long document = personValidator.documentValidator(Utils.getReader().nextLine()); 
    
    System.out.println("Ingrese el nombre de la persona"); 
    String name = personValidator.nameValidator(Utils.getReader().nextLine()); 
    
    System.out.println("Ingrese la edad de la persona"); 
    int age = personValidator.ageValidator(Utils.getReader().nextLine()); 

    System.out.println("Ingrese el userName de la persona");
		String userName = userValidator.userNameValidator(Utils.getReader().nextLine());
		
    System.out.println("Ingrese la contraseña de la persona");
		String password = userValidator.passwordValidator(Utils.getReader().nextLine());
    
    administrationService.createPerson(document, name, age, userName, password, role);
  }
}
