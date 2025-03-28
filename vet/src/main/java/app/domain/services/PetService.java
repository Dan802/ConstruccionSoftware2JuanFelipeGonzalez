package app.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.domain.models.Pet;
import app.ports.PetPort;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Service
public class PetService  {
  
  @Autowired
  private PetPort petPort;

  public void save(Pet pet) {
    
  }
}
