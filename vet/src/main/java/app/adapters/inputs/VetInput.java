package app.adapters.inputs;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.adapters.inputs.utils.PersonValidator;
import app.adapters.inputs.utils.SimpleValidator;
import app.adapters.inputs.utils.UserValidator;
import app.adapters.inputs.utils.Utils;
import app.adapters.login.entity.LoginEntity;
import app.adapters.medicalRecord.MedicalRecordAdapter;
import app.adapters.medicalRecord.repository.MedicalRecordRepository;
import app.adapters.person.PersonAdapter;
import app.adapters.pet.PetAdapter;
import app.domain.models.Login;
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

  private final String MENU = "\nMenu del Veterinario, ingrese la opción:" + 
  "\n 1. Crear dueño" + 
  "\n 2. Crear mascota" +
  "\n 3. Guardar registro medico en la historia clínica" +
  "\n 4. Consultar historia clínica (ingresando los milisegundos)" +
  "\n 5. Editar historia clínica" +
  "\n 6. Crear orden" +
  "\n 7. Consultar el listado de ordenes" +
  "\n 8. Anular orden" +
  "\n 9. Cerrar sesión";

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
        this.searchMedicalRecord();
        return true;
      }
      case "5" : {
        return true;
      }
      case "6" : {
        return true;
      }
      case "7" : {
        return true;
      }
      case "8" : {
        return true;
      }
      case "9" : {
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

  private void searchMedicalRecord() throws Exception {
    System.out.println("\nIngrese el id de la historia clínica (milisegundos PK)");
    Long miliseconds = simpleValidator.longValidator(Utils.getReader().nextLine(), "\"Id de la historia clínica\" ");
    MedicalRecord meRe = meReAdapter.findByDate(miliseconds);

    String meRePrint = 
    "\nId de la historia clínica: " + meRe.getDate() +
    "\nMédico que lo atendió: " + meRe.getVetDocument().getName() +
    "\nMascota atendida: " + meRe.getPetId().getName() +
    "\nMotivo de consulta: " + meRe.getReason() +
    "\nSintomatologia: " + meRe.getSymptoms() +
    "\nDiagnostico: " + meRe.getDiagnosis() +
    "\nProcedimiento: " + meRe.getProcedures() +
    "\nMedicamento: " + meRe.getMedicine() +
    "\nDosis de medicamento: " + meRe.getDoseMedication() +
    "\nId para la orden: " + meRe.getOrdenId() +
    "\nHistorial de vacunación: " + meRe.getVaccinationHistory() +
    "\nMedicamentos a los que presenta alergia: " + meRe.getAllergyMedications() +
    "\nDetalle del procedimiento: " + meRe.getProcedureDetail() +
    "\n¿Orden anulada? " + (meRe.isOrderCancellation() ? "Si" : "No");

    System.out.println(meRePrint);
  }

  private void createMedicalRecord(Login login) throws Exception{
    System.out.println("\nIngrese la razón de la consulta");
    String reason = simpleValidator.stringValidator(Utils.getReader().nextLine(), "\"Razón \" ");
    System.out.println("The reason is you, nanana o, nanana eiei, you are the music en mi");
    
    System.out.println("\nIngrese los sintomas ");
    String symptoms = simpleValidator.stringValidator(Utils.getReader().nextLine(), "\"Sintomas\" ");
    
    System.out.println("\nIngrese el diagnostico ");
    String diagnosis = simpleValidator.stringValidator(Utils.getReader().nextLine(), "\"Diagnostico\" ");
    
    System.out.println("\nIngrese el procedimiento ");
    String procedure = simpleValidator.stringValidator(Utils.getReader().nextLine(), "\"Procedimiento\" ");
    
    System.out.println("\nIngrese la medicina recetada");
    String medicine = simpleValidator.stringValidator(Utils.getReader().nextLine(), "\"Medicina\" ");
    
    System.out.println("\nIngrese la dosis(Texto)");
    String doseMedication = simpleValidator.stringValidator(Utils.getReader().nextLine(), "\"Dosis\" "); 
    
    System.out.println("\nIngrese el historial de vacunas ");
    String vaccinationHistory = simpleValidator.stringValidator(Utils.getReader().nextLine(), "\"Historial\" ");
    
    System.out.println("\nIngrese los medicamentos a los que es alergico");
    String allergyMedications = simpleValidator.stringValidator(Utils.getReader().nextLine(), "\"Alegico \" ");
    
    System.out.println("\nIngrese los detalles del procedimiento");
    String procedureDetail = simpleValidator.stringValidator(Utils.getReader().nextLine(), "\"Procedimiento\" ");
    
    boolean orderCancellation = false;
    
    Person veterinary = personAdapter.findByDocument(login.getPersonId().getDocument());

    Pet pet = new Pet();
    do {
      System.out.println("\nIngrese el id de la mascota");      
      Long petId = simpleValidator.longValidator(Utils.getReader().nextLine(), "\"petId\" ");
      pet = petAdapter.findByPetId(petId);

      if(pet == null) {
        System.out.println("No hay una mascota con ese id, intente de nuevo.");
      }
    } while (pet == null);

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