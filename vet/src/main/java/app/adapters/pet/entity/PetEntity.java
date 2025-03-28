package app.adapters.pet.entity;

import org.hibernate.annotations.ManyToAny;

import app.adapters.person.entity.PersonEntity;
import app.domain.models.Person;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="pet")
@Getter
@Setter
public class PetEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name="pet_id")
  private long petId; 
  
  @Column(name="name")
  private String name; 
  
  @JoinColumn(name="document_owner")
  @ManyToOne
  private PersonEntity documentOwner; 
  
  @Column(name="age")
  private int age; 
  @Column(name="specie")
  private String specie; 
  @Column(name="race")
  private String race; 
  @Column(name="description")
  private String description; 
  @Column(name="weight")
  private double weight; 
}
