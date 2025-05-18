package app.domain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Person {
    
    private long document; // Cedula
    private String name; // Nombre
    private int age; // Edad
    private String role;
        // Roles:
        // Administrador
        // Vendedor
        // Veterinario
        // Dueño
    
    public Person(long document, String name, int age, String rol) {
        this.document = document;
        this.name = name;
        this.age = age;
        this.role = rol;
    }
}
