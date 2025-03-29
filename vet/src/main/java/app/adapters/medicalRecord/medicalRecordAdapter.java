package app.adapters.medicalRecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.adapters.medicalRecord.entity.MedicalRecordEntity;
import app.adapters.medicalRecord.repository.MedicalRecordRepository;
import app.adapters.person.entity.PersonEntity;
import app.adapters.pet.entity.PetEntity;
import app.domain.models.MedicalRecord;
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
    meReEntity.setOrdenId(meRecord.getOrdenId());
    meReEntity.setOrderCancellation(meRecord.isOrderCancellation()); 
    meReEntity.setProcedures(meRecord.getProcedures());
    meReEntity.setProcedureDetail(meRecord.getProcedureDetail());
    meReEntity.setReason(meRecord.getReason());
    meReEntity.setSymptoms(meRecord.getSymptoms());
    meReEntity.setVaccinationHistory(meRecord.getVaccinationHistory());
    meReRepository.save(meReEntity);
  }

  @Override
  public MedicalRecord findByPetId(String petId) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findByPetId'");
  }

  @Override
  public MedicalRecord updateMedicalHistory(MedicalRecord medicalRecord) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'updateMedicalHistory'");
  }
  
}
