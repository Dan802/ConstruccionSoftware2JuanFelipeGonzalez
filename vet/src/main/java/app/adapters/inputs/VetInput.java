package app.adapters.inputs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.adapters.inputs.utils.PersonValidator;
import app.adapters.inputs.utils.SimpleValidator;
import app.adapters.inputs.utils.UserValidator;
import app.adapters.inputs.utils.Utils;
import app.adapters.medicalRecord.MedicalRecordAdapter;
import app.adapters.order.OrderAdapter;
import app.adapters.person.PersonAdapter;
import app.adapters.pet.PetAdapter;
import app.domain.models.Login;
import app.domain.models.MedicalRecord;
import app.domain.models.Person;
import app.domain.models.Pet;
import app.domain.services.PetService;
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
  "\n 1. Crear dueño" + 
  "\n 2. Crear mascota" +
  "\n 3. Guardar registro medico en la historia clínica" +
  "\n 4. Consultar historia clínica (ingresando los milisegundos)" +
  "\n 5. Editar historia clínica (ingresando los milisegundos)" +
  "\n 6. Consultar orden (ingresando el id de la orden)" +
  "\n 7. Anular orden" +
  "\n 8. Cerrar sesión";

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
  @Autowired
  private MedicalRecordAdapter meReAdapter;
  @Autowired
  private OrderAdapter orderAdapter;  

  public void menu(Login login) throws Exception {
    boolean controlVble = true;
    do {
      controlVble = menu2(login);
    } while (controlVble);
  }

  public boolean menu2(Login login) {
    try {
			System.out.println(MENU);
			String option = Utils.getReader().nextLine();
			switch (option) {
        case "1": {
          this.createPetOwner();
          return true;
        }
        case "2" :{
          this.createPet();
          return true;
        }
        case "3" : {
          this.createMedicalRecord(login);
          return true;
        }
        case "4" : {
          veterinaryService.showMedicalRecord();
          return true;
        }
        case "5" : {
          this.editMedicalRecord();
          return true;
        }
        case "6" : {
          veterinaryService.searchOrder();
          return true;
        }
        case "7" : {
          this.cancelOrder();
          return true;
        }
        case "8" : {
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

  private void createPetOwner() throws Exception {
    System.out.println("\nIngrese el documento del dueño"); 
    long document = personValidator.documentValidator(Utils.getReader().nextLine()); 
    
    // Todo: Primero se puede consultar y luego seguir?
    veterinaryService.notExistsPerson(document, "Ya existe una persona con esa cedula");

    System.out.println("Ingrese el nombre del dueño"); 
    String name = personValidator.nameValidator(Utils.getReader().nextLine()); 
    
    System.out.println("Ingrese la edad del dueño"); 
    int age = personValidator.ageValidator(Utils.getReader().nextLine()); 

    veterinaryService.savePetOwner(document, name, age);
  }

  private void createPet() throws Exception {
    System.out.println("\nIngrese el documento del dueño"); 
    Long documentOwner = simpleValidator.longValidator(Utils.getReader().nextLine(), "\"Documento del dueño\" ");
    
    // Validamos que el dueño exista (Mejor UX)
    Person person = veterinaryService.existsPerson(documentOwner, "No hay un dueño registrado con esa cedula");
    
    System.out.println("\nIngrese el nombre de la mascota"); 
    String name = simpleValidator.stringValidator(Utils.getReader().nextLine(), "\"Nombre\" ");
    
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

    veterinaryService.savePet(person, name, age, specie, race, description, weight);
  }

  private void createMedicalRecord(Login login) throws Exception{

    System.out.println("\nIngrese el id de la mascota");      
    Long petId = simpleValidator.longValidator(Utils.getReader().nextLine(), "\"petId\" ");
    
    // Validamos que exista la mascota (Mejor UX)
    Pet pet = veterinaryService.searchPet(petId);

    System.out.println("\nIngrese la razón de la consulta");
    String reason = simpleValidator.stringValidator(Utils.getReader().nextLine(), "\"Razón \" ");
    
    System.out.println("\nIngrese los sintomas ");
    String symptoms = simpleValidator.stringValidator(Utils.getReader().nextLine(), "\"Sintomas\" ");
    
    System.out.println("\nIngrese el diagnostico ");
    String diagnosis = simpleValidator.stringValidator(Utils.getReader().nextLine(), "\"Diagnostico\" ");
    
    System.out.println("\nIngrese el procedimiento (opcional) ");
    String procedure = Utils.getReader().nextLine();
    
    System.out.println("\nIngrese la medicina recetada (opcional)");
    String medicine = Utils.getReader().nextLine();
    
    System.out.println("\nIngrese la dosis del medicamento (opcional)");
    String doseMedication = Utils.getReader().nextLine(); 
    
    System.out.println("\nIngrese el historial de vacunas ");
    String vaccinationHistory = simpleValidator.stringValidator(Utils.getReader().nextLine(), "\"Historial\" ");
    
    System.out.println("\nIngrese los medicamentos a los que es alergico");
    String allergyMedications = simpleValidator.stringValidator(Utils.getReader().nextLine(), "\"Alegico \" ");
    
    System.out.println("\nIngrese los detalles del procedimiento");
    String procedureDetail = simpleValidator.stringValidator(Utils.getReader().nextLine(), "\"Procedimiento\" ");
    
    Person veterinary = personAdapter.findByDocument(login.getPersonId().getDocument());
    
    Long milisecondsDate = System.currentTimeMillis();

    veterinaryService.saveMedicalRecord(milisecondsDate, veterinary, pet, reason, symptoms, diagnosis, procedure, medicine, doseMedication, milisecondsDate, vaccinationHistory, allergyMedications, procedureDetail);
  }

  private void editMedicalRecord() throws Exception {
    // Buscamos e imprimimos la historia clínica
    // Así el usuario sabe que campo editar
    MedicalRecord meRe = veterinaryService.searchMedicalRecord();
    veterinaryService.printMedicalRecord(meRe);

    int option;

    do {
      System.out.println("\nElija el campo que desea editar. Ej: 1, 2, 3...");
      option = simpleValidator.intValidator(Utils.getReader().nextLine(), "\"Opción\" ");  

      switch (option) {
        case 1:
          System.out.println("\nEl id de la historia clínica no se puede modificar");
        break;

        case 2:
          veterinaryService.changeVet(meRe);
        break;

        case 3:
          veterinaryService.changePet(meRe);
        break;

        case 4:
          veterinaryService.changeReason(meRe);
        break;

        case 5:
          veterinaryService.changeSymptoms(meRe);
        break;

        case 6:
          veterinaryService.changeDiagnosis(meRe);
        break;

        case 7:
          veterinaryService.changeProcedure(meRe);
        break;

        case 8:
          veterinaryService.changeMedicine(meRe);
        break;

        case 9:
          veterinaryService.changeDoseMedication(meRe);
        break;

        case 10:
          veterinaryService.createOrder(meRe);
        break;        

        case 11:
          veterinaryService.changeVaccinationHistory(meRe);
        break;

        case 12:
          veterinaryService.changeAllergyMedications(meRe);
        break;

        case 13:
          veterinaryService.changeProcedureDeatils(meRe);
        break;

        case 14:
          veterinaryService.changeOrderCancellation(meRe);
        break;

        default:
          System.out.println("Opción no valida, no seas pendejo :)");
        break;
      }
    } while (option < 0 && option > 15);
  }

  private void cancelOrder() throws Exception{
    MedicalRecord medicalRecord = veterinaryService.searchMedicalRecord();
    veterinaryService.changeOrderCancellation(medicalRecord);
  }
}