package app.adapters.person.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="person")
@Getter
@Setter
public class PersonEntity {
  @Id
  @Column(name="document")
  private long document; // Cedula
  
  @Column(name="name")
  private String name; // Nombre

  @Column(name="age")
  private int age; // Edad

  @Column(name="role", columnDefinition = "VARCHAR(20) CHECK (UPPER(role) IN ('ADMINISTRADOR', 'VENDEDOR', 'VETERINARIO', 'DUEÑO'))")
  private String role;
}
