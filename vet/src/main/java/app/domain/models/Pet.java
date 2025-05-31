package app.domain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Pet {
  private long petId; 
  private String name; // Nombre
  private Person documentOwner; // Cedula del dueño
  private int age; // Edad
  private String specie; // Especie
  private String breed; // Raza
  private String description; // (color, tamaño, lunares...)
  private double weight; // Peso
  
  public Pet(long petId, String name, Person documentOwner, int age, String specie, String breed, String description,
      double weight) {
    this.petId = petId;
    this.name = name;
    this.documentOwner = documentOwner;
    this.age = age;
    this.specie = specie;
    this.breed = breed;
    this.description = description;
    this.weight = weight;
  }
}
