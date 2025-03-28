package app.ports;

import app.domain.models.MedicalRecord;

public interface MedicalRecordPort {
  // Crear la historia clinica
  public MedicalRecord save(MedicalRecord medicalRecord);

  // Consultar historia clinica
  public MedicalRecord findByPetId(String petId);

  // Editar historia clinica
  public MedicalRecord updateMedicalHistory(MedicalRecord medicalRecord); // Todo como se edita? 
}
