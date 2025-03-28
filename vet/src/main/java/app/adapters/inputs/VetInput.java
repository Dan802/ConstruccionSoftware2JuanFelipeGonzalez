package app.adapters.inputs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.adapters.inputs.utils.PersonValidator;
import app.adapters.inputs.utils.SimpleValidator;
import app.adapters.inputs.utils.UserValidator;
import app.adapters.inputs.utils.Utils;
import app.adapters.person.PersonAdapter;
import app.adapters.pet.PetAdapter;
import app.domain.models.MedicalRecord;
import app.domain.models.Order;
import app.domain.models.Person;
import app.domain.models.Pet;
import app.domain.services.PetService;
import app.domain.services.VeterinaryService;
import app.ports.PersonPort;
import app.ports.PetPort;
import ch.qos.logback.classic.pattern.Util;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@Getter
@Setter
@NoArgsConstructor
public class VetInput {

  private final String nextLine = Utils.getReader().nextLine();

  // todo Pueden haber dueños sin mascotas?
  private final String MENU = "\nMenu del Veterinario, ingrese la opción:" + 
  "\n 1. Crear dueño." + 
  "\n 2. Crear mascota." +
  "\n 3. Guardar registro medico en la historia clínica" +
  "\n 4. Consultar historia clínica" +
  "\n 5. Editar historia clínica." +
  "\n 6. Consultar al listado de ordenes." +
  "\n 7. Crear orden." +
  "\n 8. Anular orden (No se deben eliminar)." +
  "\n 9. Salir.";

  @Autowired
  private PersonValidator personValidator;
  @Autowired
	private UserValidator userValidator;
  @Autowired 
  private SimpleValidator simpleValidator;
  @Autowired
  private VeterinaryService veterinaryService;
  @Autowired
  private PetService petService;
  @Autowired
  private PersonAdapter personAdapter;
  @Autowired
  private PetAdapter petAdapter;

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
        this.createPet();
        return;
			}
      case "3" : {
        this.createMedicalRecord();
        return;
      }
      case "4" : {
        return;
      }
      case "5" : {
        return;
      }
      case "6" : {
        return;
      }
      case "7" : {
        return;
      }
      case "8" : {
        return;
      }
      case "9" : {
        return;
      }
			default:
				System.out.println("Opcion no valida.");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
  }

  private void createMedicalRecord() throws Exception{
    
    System.out.println("\nIngrese la razón de la consulta");
    String reason = simpleValidator.stringValidator(nextLine, "\"Razón \" ");
    System.out.println("The reason is you, nanana o, nanana eiei, you are the music en mi");
    
    System.out.println("\nIngrese los sintomas ");
    String symptoms = simpleValidator.stringValidator(nextLine, "\"Sintomas\" ");
    
    System.out.println("\nIngrese el diagnostico ");
    String diagnosis = simpleValidator.stringValidator(nextLine, "\"Diagnostico\" ");
    
    System.out.println("\nIngrese el procedimiento ");
    String procedure = simpleValidator.stringValidator(nextLine, "\"Procedimiento\" ");
    
    System.out.println("\nIngrese la medicina recetada");
    String medicine = simpleValidator.stringValidator(nextLine, "\"Medicina\" ");
    
    System.out.println("\nIngrese la dosis(Texto)");
    String doseMedication = simpleValidator.stringValidator(nextLine, "\"Dosis\" "); 
    
    System.out.println("\nIngrese el historial de vacunas ");
    String vaccinationHistory = simpleValidator.stringValidator(nextLine, "\"Historial\" ");
    
    System.out.println("\nIngrese los medicamentos a los que es alergico");
    String allergyMedications = simpleValidator.stringValidator(nextLine, "\"Alegico \" ");
    
    System.out.println("\nIngrese los detalles del procedimiento");
    String procedureDetail = simpleValidator.stringValidator(nextLine, "\"Procedimiento\" ");
    
    boolean orderCancellation = false;
    Person veterinary;
    String ordenId; 

  }

  private void createPet() throws Exception {
    System.out.println("\nIngrese el nombre de la mascota"); 
    String name = simpleValidator.stringValidator(Utils.getReader().nextLine(), "\"Nombre\" ");
    System.out.println("\nIngrese el documento del dueño"); 
    Long documentOwner = simpleValidator.longValidator(Utils.getReader().nextLine(), "\"Documento del dueño\" ");
    
    Person person = personAdapter.findByDocument(documentOwner);
    if(person == null) {
      throw new Exception("No hay un dueño registrado con esa cedula");
    }

    System.out.println("\nIngrese la edad de la mascota"); 
    int age = simpleValidator.intValidator(Utils.getReader().nextLine(), "\"Edad\" ");
    System.out.println("\nIngrese la especie de la mascota"); 
    String specie = simpleValidator.stringValidator(Utils.getReader().nextLine(), "\"Especie\" ");
    System.out.println("\nIngrese la raza de la mascota"); 
    String race = simpleValidator.stringValidator(Utils.getReader().nextLine(), "\"Raza\" ");
    System.out.println("\nIngrese la descripción de la mascota"); 
    String description = simpleValidator.stringValidator(Utils.getReader().nextLine(), "\"Peso\" ");
    System.out.println("\nIngrese el peso de la mascota"); 
    double weight = simpleValidator.doubleValidator(Utils.getReader().nextLine(), "\"Peso\" ");

    Pet newPet = new Pet();
    newPet.setName(name);
    newPet.setDocumentOwner(person);
    newPet.setAge(age);
    newPet.setSpecie(specie);
    newPet.setRace(race);
    newPet.setDescription(description);
    newPet.setWeight(weight);

    petAdapter.save(newPet);
    System.out.println("\nLa mascota ha sido añadida correctamente.");
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
