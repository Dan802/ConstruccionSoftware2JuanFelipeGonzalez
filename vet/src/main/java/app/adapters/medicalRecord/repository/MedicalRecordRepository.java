package app.adapters.medicalRecord.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.adapters.medicalRecord.entity.MedicalRecordEntity;
import app.domain.models.MedicalRecord;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecordEntity, Long> {
  public MedicalRecordEntity findByDate(Long miliseconds);
}
