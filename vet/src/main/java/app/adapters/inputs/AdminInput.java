package app.adapters.inputs;

import org.springframework.stereotype.Component;

import app.domain.models.Person;
import app.ports.InputPort;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Component
public class AdminInput implements InputPort {
  @Override
  public void menu() throws Exception {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'menu'");
  }
}
