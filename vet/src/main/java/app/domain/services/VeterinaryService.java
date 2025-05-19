package app.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.Exceptions.BusinessException;
import app.adapters.inputs.utils.SimpleValidator;
import app.adapters.inputs.utils.Utils;
import app.adapters.medicalRecord.MedicalRecordAdapter;
import app.adapters.person.PersonAdapter;
import app.adapters.pet.PetAdapter;
import app.domain.models.Login;
import app.domain.models.MedicalRecord;
import app.domain.models.Person;
import app.domain.models.Pet;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Service
public class VeterinaryService {

  @Autowired
  private PersonAdapter personAdapter;
  @Autowired 
  private SimpleValidator simpleValidator;
  @Autowired
  private MedicalRecordAdapter meReAdapter;
  @Autowired
  private PetAdapter petAdapter;
  

  //#region CREATE
  public void savePetOwner(long document, String name, int age) throws Exception {
    notExistsPerson(document, "There is already a person with that document");

    String role = "Dueño";
    Person newPerson = new Person(document, name, age, role);

    personAdapter.save(newPerson);
    System.out.println("\n The owner has been saved correctly.");
  }
  
  public Pet savePet(long documentOwner, String name, int age, String specie, String race, String description, double weight) throws Exception {
      
      Person person = existsPerson(documentOwner, "There is no owner registered with that document");

      Pet newPet = new Pet();
      newPet.setName(name);
      newPet.setDocumentOwner(person);
      newPet.setAge(age);
      newPet.setSpecie(specie);
      newPet.setRace(race);
      newPet.setDescription(description);
      newPet.setWeight(weight);

      petAdapter.save(newPet);
      System.out.println("\nThe pet has been added correctly.");
      return newPet;
    }

  public void saveMedicalRecord(MedicalRecord medicalRecord, String msg) {
    meReAdapter.save(medicalRecord);
    System.out.println(msg);
  }

  public MedicalRecord saveMedicalRecord(Long milisecondsDate, Person veterinary, Pet pet, String reason, String symptoms, String diagnosis, String procedure, String medicine, String doseMedication, String vaccinationHistory, String allergyMedications, String procedureDetail) {
    
    if(milisecondsDate == null) {
      milisecondsDate = System.currentTimeMillis();
    }

    boolean orderCancellation = false;

    MedicalRecord meRe = new MedicalRecord(milisecondsDate,veterinary, pet, reason,symptoms,diagnosis, procedure, medicine, doseMedication, vaccinationHistory, allergyMedications, procedureDetail, orderCancellation);
    meRe = meReAdapter.save(meRe);
    
    System.out.println("\nLa historia clínica ha sido guardada correctamente.");
    return meRe;
  }
  //#endregion CREATE
  
  //#region UPDATED
  public void changeVet(Long personId, MedicalRecord medicalRecord) throws Exception {
    Person person = personAdapter.findByDocument(personId);
    
    if(person == null){
      throw new Exception("No hay un vet con ese documento, intente de nuevo");
    }

    medicalRecord.setVetDocument(person);
    saveMedicalRecord(medicalRecord, "El vet ha sido cambiado exitosamente");
  }

  public void changePet(Long petId, MedicalRecord meRe) throws Exception {
    Pet pet = petAdapter.findByPetId(petId);

    if(pet == null) {
      throw new Exception("No hay una mascota con ese id, intente de nuevo.");
    } 
    
    meRe.setPetId(pet);
    saveMedicalRecord(meRe, "La mascota ha sido cambiada exitosamente");
  }

  public void changeReason(MedicalRecord meRe) throws Exception {
    System.out.println("\nIngrese la razón de la consulta");
    String reason = simpleValidator.stringValidator(Utils.getReader().nextLine(), "\"Razón \" ");
    meRe.setReason(reason);
    saveMedicalRecord(meRe, "La razón ha sido cambiada exitosamente");
  }

  public void changeSymptoms(MedicalRecord meRe) throws Exception {
    System.out.println("\nIngrese los sintomas ");
    String symptoms = simpleValidator.stringValidator(Utils.getReader().nextLine(), "\"Sintomas\" ");
    meRe.setSymptoms(symptoms);
    saveMedicalRecord(meRe, "Los sintomas han sido cambiados exitosamente");
  }

  public void changeDiagnosis(MedicalRecord meRe) throws Exception {
    System.out.println("\nIngrese el diagnostico ");
    String diagnosis = simpleValidator.stringValidator(Utils.getReader().nextLine(), "\"Diagnostico\" ");
    meRe.setDiagnosis(diagnosis);
    saveMedicalRecord(meRe, "El diagnostico ha sido cambiado exitosamente");
  }

  public void changeMedicine(MedicalRecord meRe) throws Exception {
    System.out.println("\nIngrese la medicina recetada (opcional)");
    String medicine = simpleValidator.stringValidator(Utils.getReader().nextLine(), "");
    meRe.setMedicine(medicine);
    saveMedicalRecord(meRe, "La medicina ha sido cambiada exitosamente");
  }

  public void changeDoseMedication(MedicalRecord meRe) throws Exception {
    System.out.println("\nIngrese la dosis (opcional)");
    String doseMedication = simpleValidator.stringValidator(Utils.getReader().nextLine(), "") ;
    meRe.setDoseMedication(doseMedication);
    saveMedicalRecord(meRe, "La dosis ha sido cambiada exitosamente");
  }

  public void changeProcedure(MedicalRecord meRe) throws Exception {
    System.out.println("\nIngrese el procedimiento (opcional)");
    String procedure = simpleValidator.stringValidator(Utils.getReader().nextLine(), "");
    meRe.setProcedures(procedure);
    saveMedicalRecord(meRe, "El procedimiento ha sido cambiado exitosamente");
  }

  public void changeVaccinationHistory(MedicalRecord meRe) throws Exception {
    System.out.println("\nIngrese el historial de vacunas ");
    String vaccinationHistory = simpleValidator.stringValidator(Utils.getReader().nextLine(), "\"Historial\" ");
    meRe.setVaccinationHistory(vaccinationHistory);
    saveMedicalRecord(meRe, "El historial de vacunas ha sido cambiada exitosamente");
  }

  public void changeAllergyMedications(MedicalRecord meRe) throws Exception {
    System.out.println("\nIngrese los medicamentos a los que es alergico");
    String allergyMedications = simpleValidator.stringValidator(Utils.getReader().nextLine(), "\"Alegico \" ");
    meRe.setAllergyMedications(allergyMedications);
    saveMedicalRecord(meRe, "Los medicamentos a los que es alergico han sido cambiados exitosamente");
  }

  public void changeProcedureDeatils(MedicalRecord meRe) throws Exception {
    System.out.println("\nIngrese los detalles del procedimiento");
    String procedureDetail = simpleValidator.stringValidator(Utils.getReader().nextLine(), "\"Procedimiento\" ");
    meRe.setProcedureDetail(procedureDetail);
    saveMedicalRecord(meRe, "Los detalles del procedimiento han sido cambiados exitosamente");
  }

  public void changeOrderCancellation(MedicalRecord meRe) throws Exception {
    meRe.setOrderCancellation(true); 
    saveMedicalRecord(meRe, "\nLa orden ha sido anulada correctamente");
  }
  //#endregion UPDATED

  //#region SEARCH
  /**
   * Si existe devuelve la persona
   * @param document
   * @param msg
   * @return Person
   * @throws Exception
   */
  public Person existsPerson(long document, String msg) throws BusinessException {
    Person person = personAdapter.findByDocument(document);
    if(person == null) throw new BusinessException(msg);
    return person;
  }

  /**
   * Si la persona existe devuelve error
   * @param document
   * @param msg
   * @throws Exception
   */
  public void notExistsPerson(long document, String msg) throws BusinessException {
    Person person = personAdapter.findByDocument(document);
    if(person != null) throw new BusinessException(msg);
  }

  public Pet searchPet() throws Exception{
    System.out.println("\nIngrese el id de la mascota");      
    Long petId = simpleValidator.longValidator(Utils.getReader().nextLine(), "\"petId\" ");
    
    Pet pet = petAdapter.findByPetId(petId);
    if(pet == null) throw new Exception("No hay una mascota registrada con ese id");
    return pet;
  }

  public MedicalRecord searchMedicalRecord(long miliseconds) throws Exception {
    MedicalRecord meRe = meReAdapter.findByDate(miliseconds);

    if(meRe == null) throw new Exception("\nNo se encontró la historia clínica");
    return meRe;
  }
  
  //#endregion SEARCH

  //#region PRINT
  public void printMedicalRecord(MedicalRecord meRe) {
    String meRePrint = 
      "\n1. Id de la historia clínica: " + meRe.getDate() +
      "\n1. Id de la historia clínica: " + Utils.mstoDate(meRe.getDate()) +
      "\n2. Médico que lo atendió: " + meRe.getVetDocument().getName() +
      "\n3. Mascota atendida: " + meRe.getPetId().getName() +
      "\n4. Motivo de consulta: " + meRe.getReason() +
      "\n5. Sintomatologia: " + meRe.getSymptoms() +
      "\n6. Diagnostico: " + meRe.getDiagnosis() +
      "\n7. Procedimiento (opcional): " + simpleValidator.isNull(meRe.getProcedures())  +
      "\n8. Medicamento (opcional): " + simpleValidator.isNull(meRe.getMedicine()) +
      "\n9. Dosis de medicamento (opcional): " + simpleValidator.isNull(meRe.getDoseMedication()) +
      "\n10. Historial de vacunación: " + meRe.getVaccinationHistory() +
      "\n11. Medicamentos a los que presenta alergia: " + meRe.getAllergyMedications() +
      "\n12. Detalle del procedimiento: " + meRe.getProcedureDetail() +
      "\n13. ¿Orden anulada? " + (meRe.isOrderCancellation() ? "Si" : "No");

    System.out.println(meRePrint);
  }
  
  public void showMedicalRecord( MedicalRecord medicalRecord) throws Exception {
    printMedicalRecord(medicalRecord);
  }

  public Person searchPerson(Login login) {
    return personAdapter.findByDocument(login.getPersonId().getDocument());
  }
  //#endregion PRINT
}