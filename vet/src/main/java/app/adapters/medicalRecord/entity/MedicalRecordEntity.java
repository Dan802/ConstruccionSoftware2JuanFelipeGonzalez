package app.adapters.medicalRecord.entity;

import app.adapters.order.entity.OrderEntity;
import app.adapters.person.entity.PersonEntity;
import app.adapters.pet.entity.PetEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
  @Column(name="date")
  private Long date;
  
  @JoinColumn(name="vet_document")
  @ManyToOne // MUCHAS historias clinicas pueden tener el mismo vet
  private PersonEntity vetDocument; 

  @JoinColumn(name="pet_id")
  @ManyToOne // Muchas historias clinicas pueden pertenecer a una mascota
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
  
  @JoinColumn(name="orden_id")
  @OneToOne
  private OrderEntity ordenId; 
  
  @Column(name="vaccination_history")
  private String vaccinationHistory; 
  
  @Column(name="allergy_medications")
  private String allergyMedications; 
  
  @Column(name="procedure_detail")
  private String procedureDetail; 
  
  @Column(name="order_cancellation")
  private boolean orderCancellation; 
}
