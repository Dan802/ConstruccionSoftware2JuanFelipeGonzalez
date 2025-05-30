package app.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.Exceptions.BusinessException;
import app.Exceptions.NotFoundException;
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

  public MedicalRecord saveMedicalRecord(MedicalRecord medicalRecord, String msg) {
    meReAdapter.save(medicalRecord);
    return medicalRecord;
  }

  public MedicalRecord saveMedicalRecord(Long milisecondsDate, Person veterinary, Pet pet, String reason, String symptoms, String diagnosis, String procedure, String medicine, String doseMedication, String vaccinationHistory, String allergyMedications, String procedureDetail) {
    
    if(milisecondsDate == null) {
      milisecondsDate = System.currentTimeMillis();
    }

    boolean orderCancellation = false;

    MedicalRecord meRe = new MedicalRecord(milisecondsDate,veterinary, pet, reason,symptoms,diagnosis, procedure, medicine, doseMedication, vaccinationHistory, allergyMedications, procedureDetail, orderCancellation);
    meRe = meReAdapter.save(meRe);
    
    System.out.println("\nMEDICAL RECORD:" + meRe.toString());
    return meRe;
  }
  //#endregion CREATE
  

  //#region SEARCH
  /**
   * Si existe devuelve la persona
   * @param document
   * @param msg
   * @return Person
   * @throws Exception
   */
  public Person existsPerson(long document, String msg) throws NotFoundException {
    Person person = personAdapter.findByDocument(document);
    if(person == null) throw new NotFoundException(msg);
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

  public Pet searchPet(long petId) throws NotFoundException{
    Pet pet = petAdapter.findByPetId(petId);
    if(pet == null) throw new NotFoundException("There is no pet registered with that id");
    return pet;
  }

  public MedicalRecord searchMedicalRecord(long miliseconds) throws NotFoundException {
    MedicalRecord meRe = meReAdapter.findByDate(miliseconds);

    if(meRe == null) throw new NotFoundException("\nMedical record not found");
    return meRe;
  }
  
  //#endregion SEARCH

  public void updateMedicalRecord(MedicalRecord meRe) {
    saveMedicalRecord(meRe, "\nMedical record updated successfully");
  }

  public Person searchPerson(Login login) {
    return personAdapter.findByDocument(login.getPersonId().getDocument());
  }

  public MedicalRecord changeOrderCancellation(MedicalRecord meRe) throws Exception {
    meRe.setOrderCancellation(true); 
    return saveMedicalRecord(meRe, "Order cancelled successfully");
  }
}