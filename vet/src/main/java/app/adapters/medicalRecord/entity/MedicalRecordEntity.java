package app.adapters.medicalRecord.entity;

import java.sql.Date;

import app.adapters.person.entity.PersonEntity;
import app.adapters.pet.entity.PetEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="medical_record")
@Getter
@Setter
public class MedicalRecordEntity {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="medical_record_id")
  private long medicalRecordId;
  
  @Column(name="date")
  private Date date;
  
  @JoinColumn(name="vet_document")
  @ManyToOne // UN vet puede tener MUCHAS historias clinicas
  private PersonEntity vetDocument; 

  @JoinColumn(name="pet_id")
  @OneToOne
  private PetEntity petId;
  
  @Column(name="reason")
  private String reason; 
  
  @Column(name="symptoms")
  private String symptoms; 
  
  @Column(name="diagnosis")
  private String diagnosis; 
  
  @Column(name="procedures")
  private String procedures; 
  
  @Column(name="medicine")
  private String medicine; 
  
  @Column(name="dose_medication")
  private String doseMedication;  
  
  @Column(name="orden_id")
  // @GeneratedValue(strategy = GenerationType.UUID) Todo como lo genero entonces? 
  private String ordenId; 
  
  @Column(name="vaccination_history")
  private String vaccinationHistory; 
  
  @Column(name="allergy_medications")
  private String allergyMedications; 
  
  @Column(name="procedure_detail")
  private String procedureDetail; 
  
  @Column(name="order_cancellation")
  private boolean orderCancellation; 
}
