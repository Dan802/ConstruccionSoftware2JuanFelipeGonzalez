package app.ports;

import app.domain.models.Person;

public interface AdministrationPort {
  public Person createAdmin();
  public Person createSeller(); // Registrar Vendedor
  public Person createVeterinary(); // Registrar Medico veterinario
}
