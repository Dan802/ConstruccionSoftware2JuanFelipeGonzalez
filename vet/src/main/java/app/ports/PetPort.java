package app.ports;

import app.domain.models.Pet;

public interface PetPort {
  public void savePet(Pet petPort);
}
