package app.adapters.inputs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.adapters.inputs.utils.PersonValidator;
import app.adapters.inputs.utils.UserValidator;
import app.adapters.inputs.utils.Utils;
import app.domain.models.Person;
import app.domain.services.VeterinaryService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@Getter
@Setter
@NoArgsConstructor
public class VetInput {

  private final String MENU = "\nMenu del Veterinario, ingrese la opción:" + 
  "\n 1. Crear dueño." + 
  "\n 2. Crear mascota." +
  "\n 3. Guardar registro medico" +
  "\n 4. Consultar historia clinica" +
  "\n 5. Editar historia clinica." +
  "\n 6. Consultar al listado de ordenes." +
  "\n 7. Crear orden." +
  "\n 8. Anular orden (No se deben eliminar)." +
  "\n 9. Salir.";

  @Autowired
  private PersonValidator personValidator;
  @Autowired
	private UserValidator userValidator;
  @Autowired
  private VeterinaryService veterinaryService;

  public void menu() throws Exception {
    try {
			System.out.println(MENU);
			String option = Utils.getReader().nextLine();
			switch (option) {
			case "1": {
        this.createPetOwner();
        return;
			}
			case "2" :{
        return;
			}
      case "3" : {
        return;
      }
			default:
				System.out.println("Opcion no valida.");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
  }

  private void createPetOwner() throws Exception {
    System.out.println("\nIngrese el documento del dueño"); 
    long document = personValidator.documentValidator(Utils.getReader().nextLine()); 
    
    System.out.println("Ingrese el nombre del dueño"); 
    String name = personValidator.nameValidator(Utils.getReader().nextLine()); 
    
    System.out.println("Ingrese la edad del dueño"); 
    int age = personValidator.ageValidator(Utils.getReader().nextLine()); 
    
    Person newPerson = new Person();
    newPerson.setDocument(document);
    newPerson.setName(name);
    newPerson.setAge(age);
    newPerson.setRole("Dueño");

    veterinaryService.registerPetOwner(newPerson);
    System.out.println("\n El dueño ha sido guardado correctamente.");
  }

}
