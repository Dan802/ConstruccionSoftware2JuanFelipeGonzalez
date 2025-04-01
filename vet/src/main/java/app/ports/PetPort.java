package app.ports;

import app.domain.models.Pet;

public interface PetPort {
  public void save(Pet pet);
  public Pet findByPetId(Long petId);
}
