package app.ports;

import app.domain.models.MedicalRecord;

public interface MedicalRecordPort {
  // Crear la historia clinica
  public void save(MedicalRecord medicalRecord);

  // Consultar historia clinica
  public MedicalRecord findByDate(Long date);
}
