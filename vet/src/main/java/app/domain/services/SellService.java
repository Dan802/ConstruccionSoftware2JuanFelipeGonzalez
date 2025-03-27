package app.domain.services;

import java.util.List;

import org.springframework.stereotype.Service;

import app.domain.models.Invoice;
import app.domain.models.Order;
import app.ports.SellPort;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Service
public class SellService {

  public Invoice generateInvoice() {
    return null; // todoNull
  }

  // Suministrar medicamentos
  public void supplyMedicine (Order order) {
    generateInvoice();
  }

  // Vender otro tipo de productor
  public void sellMedicine() {
    generateInvoice();
  }
}
