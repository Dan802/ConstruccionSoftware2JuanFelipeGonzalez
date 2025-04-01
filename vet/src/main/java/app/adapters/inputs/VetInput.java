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
import app.domain.models.Order;
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
        this.showMedicalRecord();
        return true;
      }
      case "5" : {
        this.editMedicalRecord();
        return true;
      }
      case "6" : {
        this.searchOrder();
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

  private void cancelOrder() throws Exception{
    MedicalRecord medicalRecord = searchMedicalRecord();
    if(medicalRecord == null) {
      System.out.println("\nNo se encontró la historia clínica.");
      return;
    }
    setOrderCancellation(medicalRecord);
  }

  private void setOrderCancellation (MedicalRecord medicalRecord) {
    medicalRecord.setOrderCancellation(true); 
    veterinaryService.saveMedicalRecord(medicalRecord);
  }

  private Order searchOrder() throws Exception {
    System.out.println("\nIngrese el id de la orden");
    Long orderId = simpleValidator.longValidator(Utils.getReader().nextLine(), "\"Order Id\" ");
    Order order = orderAdapter.findByOrderId(orderId);

    if(order == null) {
      System.out.println("\nNo se encontró la orden.");
      return null;
    }
    veterinaryService.printOrder(order);
    return order;
  }

  private MedicalRecord searchMedicalRecord() throws Exception {
    System.out.println("\nIngrese el id de la historia clínica (milisegundos PK)");
    Long miliseconds = simpleValidator.longValidator(Utils.getReader().nextLine(), "\"Id de la historia clínica\" ");
    MedicalRecord meRe = meReAdapter.findByDate(miliseconds);

    if(meRe == null) {
      System.out.println("\nNo se encontró la historia clínica");
      return null;
    }
    return meRe;
  }

  private void showMedicalRecord() throws Exception {
    MedicalRecord medicalRecord = searchMedicalRecord();
    if(medicalRecord == null) return;

    veterinaryService.printMedicalRecord(medicalRecord);
  }

  private void createOrder(MedicalRecord meRe, Pet pet, Person owner, Person vet) {
    
    Long milisecondsDate = System.currentTimeMillis();

    Order order = new Order();
    order.setMedicalRecordId(meRe);
    order.setPetId(pet);
    order.setDocumentOwner(owner);
    order.setDocumentVet(vet);
    order.setMedicine(meRe);
    order.setCreatedDate(milisecondsDate);
    
    orderAdapter.save(order);
    System.out.println("\nSe ha creado una nueva orden con referencia a la historia clínica correctamente.");
  }

  private void createMedicalRecord(Login login) throws Exception{

    Pet pet = new Pet();
    do {
      System.out.println("\nIngrese el id de la mascota");      
      Long petId = simpleValidator.longValidator(Utils.getReader().nextLine(), "\"petId\" ");
      pet = petAdapter.findByPetId(petId);

      if(pet == null) {
        System.out.println("No hay una mascota con ese id, intente de nuevo.");
      }
    } while (pet == null);

    System.out.println("\nIngrese la razón de la consulta");
    String reason = simpleValidator.stringValidator(Utils.getReader().nextLine(), "\"Razón \" ");
    System.out.println("Developer note: The reason is you, nanana o, nanana eiei, you are the music en mi");
    
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
    
    boolean orderCancellation = false;
    
    Person veterinary = personAdapter.findByDocument(login.getPersonId().getDocument());
    
    Long milisecondsDate = System.currentTimeMillis();

    MedicalRecord meRe = new MedicalRecord();
    meRe.setDate(milisecondsDate);
    meRe.setVetDocument(veterinary);
    meRe.setPetId(pet);
    meRe.setReason(reason);
    meRe.setSymptoms(symptoms);
    meRe.setDiagnosis(diagnosis);
    meRe.setProcedures(procedure);
    meRe.setMedicine(medicine);
    meRe.setDoseMedication(doseMedication);
    meRe.setOrdenId(milisecondsDate);
    meRe.setVaccinationHistory(vaccinationHistory);
    meRe.setAllergyMedications(allergyMedications);
    meRe.setProcedureDetail(procedureDetail);
    meRe.setOrderCancellation(orderCancellation);
    meReAdapter.save(meRe);
    System.out.println("\nLa historia clínica ha sido guardada correctamente.");

    createOrder(meRe, pet, pet.getDocumentOwner(), veterinary);
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

  private void editMedicalRecord() throws Exception {
    MedicalRecord meRe = searchMedicalRecord();
    veterinaryService.printMedicalRecord(meRe);
    int opcion;
    do {
      System.out.println("\nElija el campo que desea editar (Número)");
      opcion = simpleValidator.intValidator(Utils.getReader().nextLine(), "\"Opción\" ");  

      switch (opcion) {
        case 1:
        System.out.println("\nEl id de la historia clínica no se puede modificar");
        return;

        case 2:
          System.out.println("ADVERTENCIA: Esta a punto de cambiar el veterinario al cual referencia la historia clínica");

          Person person = new Person();
            do {
              System.out.println("\nIngrese el documento del vet");      
              Long personId = simpleValidator.longValidator(Utils.getReader().nextLine(), "\"personId\" ");
              person = personAdapter.findByDocument(personId);

              if(person == null) {
                System.out.println("No hay un vet con ese documento, intente de nuevo.");
              }
            } while (person == null);
            meRe.setVetDocument(person);
        break;

        case 3:
          System.out.println("ADVERTENCIA: Esta a punto de cambiar la mascota a la cual referencia la historia clínica");
          Pet pet = new Pet();
          do {
            System.out.println("\nIngrese el id de la mascota");      
            Long petId = simpleValidator.longValidator(Utils.getReader().nextLine(), "\"petId\" ");
            pet = petAdapter.findByPetId(petId);

            if(pet == null) {
              System.out.println("No hay una mascota con ese id, intente de nuevo.");
            }
          } while (pet == null);
          meRe.setPetId(pet);
        break;

        case 4:
        System.out.println("\nIngrese la razón de la consulta");
        String reason = simpleValidator.stringValidator(Utils.getReader().nextLine(), "\"Razón \" ");
        meRe.setReason(reason);
        break;

        case 5:
        System.out.println("\nIngrese los sintomas ");
        String symptoms = simpleValidator.stringValidator(Utils.getReader().nextLine(), "\"Sintomas\" ");
        meRe.setSymptoms(symptoms);
        break;

        case 6:
        System.out.println("\nIngrese el diagnostico ");
        String diagnosis = simpleValidator.stringValidator(Utils.getReader().nextLine(), "\"Diagnostico\" ");
        meRe.setDiagnosis(diagnosis);
        break;

        case 7:
        System.out.println("\nIngrese el procedimiento (opcional)");
        String procedure = Utils.getReader().nextLine();
        meRe.setProcedures(procedure);
        break;

        case 8:
        System.out.println("\nIngrese la medicina recetada (opcional)");
        String medicine = Utils.getReader().nextLine();
        meRe.setMedicine(medicine);
        break;

        case 9:
        System.out.println("\nIngrese la dosis (opcional)");
        String doseMedication = Utils.getReader().nextLine(); 
        meRe.setDoseMedication(doseMedication);
        break;

        case 10:
        System.out.println("\nSi continua se creará una nueva orden y se perderá referencia con la anterior, desea continuar?");
        //createOrder(meRe, pet, pet.getDocumentOwner(), veterinary);
        break;        

        case 11:
        System.out.println("\nIngrese el historial de vacunas ");
        String vaccinationHistory = simpleValidator.stringValidator(Utils.getReader().nextLine(), "\"Historial\" ");
        meRe.setVaccinationHistory(vaccinationHistory);
        break;

        case 12:
        System.out.println("\nIngrese los medicamentos a los que es alergico");
        String allergyMedications = simpleValidator.stringValidator(Utils.getReader().nextLine(), "\"Alegico \" ");
        meRe.setAllergyMedications(allergyMedications);
        break;

        case 13:
        System.out.println("\nIngrese los detalles del procedimiento");
        String procedureDetail = simpleValidator.stringValidator(Utils.getReader().nextLine(), "\"Procedimiento\" ");
        meRe.setProcedureDetail(procedureDetail);
        break;

        case 14:
        setOrderCancellation(meRe);
        break;

        default:
        System.out.println("Opción no valida, no seas pendejo :)");
        break;
      }
    
      meReAdapter.save(meRe);
      System.out.println("\nLa historia clínica ha sido editada correctamente.");
    } while (opcion < 0 && opcion > 15);
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