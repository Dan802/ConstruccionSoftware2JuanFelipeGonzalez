package app.adapters.medicalRecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.adapters.medicalRecord.entity.MedicalRecordEntity;
import app.adapters.medicalRecord.repository.MedicalRecordRepository;
import app.adapters.person.entity.PersonEntity;
import app.adapters.pet.entity.PetEntity;
import app.domain.models.MedicalRecord;
import app.domain.models.Person;
import app.domain.models.Pet;
import app.ports.MedicalRecordPort;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Service
@Getter
@Setter
@NoArgsConstructor
public class MedicalRecordAdapter implements MedicalRecordPort {

  @Autowired
  public MedicalRecordRepository meReRepository;

  @Override
  public void save(MedicalRecord meRecord) {

    PersonEntity personEntity = new PersonEntity();
    personEntity.setAge(meRecord.getVetDocument().getAge());
    personEntity.setDocument(meRecord.getVetDocument().getDocument());
    personEntity.setName(meRecord.getVetDocument().getName());
    personEntity.setRole(meRecord.getVetDocument().getRole());

    PetEntity petEntity = new PetEntity();
    petEntity.setAge(meRecord.getPetId().getAge());
    petEntity.setDescription(meRecord.getPetId().getDescription());
    petEntity.setDocumentOwner(personEntity);
    petEntity.setName(meRecord.getPetId().getName());
    petEntity.setPetId(meRecord.getPetId().getPetId());
    petEntity.setRace(meRecord.getPetId().getRace());
    petEntity.setSpecie(meRecord.getPetId().getSpecie());
    petEntity.setWeight(meRecord.getPetId().getWeight());

    MedicalRecordEntity meReEntity = new MedicalRecordEntity();

    meReEntity.setDate(meRecord.getDate());
    meReEntity.setVetDocument(personEntity);
    meReEntity.setPetId(petEntity);
    meReEntity.setAllergyMedications(meRecord.getAllergyMedications());
    meReEntity.setDiagnosis(meRecord.getDiagnosis());
    meReEntity.setDoseMedication(meRecord.getDoseMedication());
    meReEntity.setMedicine(meRecord.getMedicine());
    // meReEntity.setOrdenId(meRecord.getOrdenId());
    meReEntity.setOrderCancellation(meRecord.isOrderCancellation()); 
    meReEntity.setProcedures(meRecord.getProcedures());
    meReEntity.setProcedureDetail(meRecord.getProcedureDetail());
    meReEntity.setReason(meRecord.getReason());
    meReEntity.setSymptoms(meRecord.getSymptoms());
    meReEntity.setVaccinationHistory(meRecord.getVaccinationHistory());
    meReRepository.save(meReEntity);
  }

  @Override
  public MedicalRecord findByDate(Long miliseconds) {
    MedicalRecordEntity medicalRecordEntity = meReRepository.findByDate(miliseconds);
    if(medicalRecordEntity == null) return null;
    return medicalRecordAdapter(medicalRecordEntity);
  }

  public MedicalRecord medicalRecordAdapter(MedicalRecordEntity meReEntity) {
    Person person = new Person();
    person.setAge(meReEntity.getVetDocument().getAge());
    person.setDocument(meReEntity.getVetDocument().getDocument());
    person.setName(meReEntity.getVetDocument().getName());
    person.setRole(meReEntity.getVetDocument().getRole());

    Pet pet = new Pet();
    pet.setAge(meReEntity.getPetId().getAge());
    pet.setDescription(meReEntity.getPetId().getDescription());
    pet.setDocumentOwner(person);
    pet.setName(meReEntity.getPetId().getName());
    pet.setPetId(meReEntity.getPetId().getPetId());
    pet.setRace(meReEntity.getPetId().getRace());
    pet.setSpecie(meReEntity.getPetId().getSpecie());
    pet.setWeight(meReEntity.getPetId().getWeight());

    MedicalRecord meRe = new MedicalRecord();
    meRe.setAllergyMedications(meReEntity.getAllergyMedications());
    meRe.setDate(meReEntity.getDate());
    meRe.setDiagnosis(meReEntity.getDiagnosis());
    meRe.setDoseMedication(meReEntity.getDoseMedication());
    meRe.setMedicine(meReEntity.getMedicine());
    // meRe.setOrdenId(meReEntity.getOrdenId());
    meRe.setOrderCancellation(meReEntity.isOrderCancellation());
    meRe.setPetId(pet);
    meRe.setProcedureDetail(meReEntity.getProcedureDetail());
    meRe.setProcedures(meReEntity.getProcedures());
    meRe.setReason(meReEntity.getReason());
    meRe.setSymptoms(meReEntity.getSymptoms());
    meRe.setVaccinationHistory(meReEntity.getVaccinationHistory());
    meRe.setVetDocument(person);

    return meRe;
  }
}