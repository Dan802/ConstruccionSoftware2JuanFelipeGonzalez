package app.adapters.order.entity;

import app.adapters.medicalRecord.entity.MedicalRecordEntity;
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
// El nombre de la tabla no puede ser order
// unas cuantas horas en descubrirlo :' 
@Table(name="orders") 
@Getter
@Setter
public class OrderEntity {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="order_id")
  private Long orderId;

  @JoinColumn(name="medical_record_id")
  @OneToOne // UNA orden pertenece a UNA historia clinica
  private MedicalRecordEntity medicalRecordId;

  @JoinColumn(name="pet")
  @ManyToOne // MUCHAS ordenes pueden tener la misma mascota
  private PetEntity pet;

  @JoinColumn(name="document_owner")
  @ManyToOne // MUCHAS ordenes pueden tener el misma dueño 
  private PersonEntity documentOwner;

  @JoinColumn(name="document_vet")
  @ManyToOne // MUCHAS ordenes pueden ser creadas por el mismo veterinario 
  private PersonEntity documentVet;

  @JoinColumn(name="medicine")
  @OneToOne // UNA orden contiene UNA medicina
  private MedicalRecordEntity medicine;

  @Column(name="created_date")
  private Long createdDate;
}
