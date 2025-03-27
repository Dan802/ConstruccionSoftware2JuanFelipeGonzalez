package app.domain.services;

import org.springframework.stereotype.Service;

import app.domain.models.Person;
import app.domain.models.Pet;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Service
public class VeterinaryService {

  public Person createOwner(){
    return null;
  }
  public Pet createPet(){
    return null;
  }
}
