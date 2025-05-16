package app.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.adapters.order.OrderAdapter;
import app.domain.models.Order;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Service
public class SellerService {

  @Autowired
  private OrderAdapter orderAdapter;
  
  public void existsMedicine(Order order) throws Exception {
    if(order.getMedicine().getMedicine().length() == 0) {
      throw new Exception("\nNo hay medicinas asociadas a la orden suministrada");
    }
  }
}
