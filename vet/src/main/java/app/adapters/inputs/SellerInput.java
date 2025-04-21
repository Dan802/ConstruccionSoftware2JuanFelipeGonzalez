package app.adapters.inputs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.adapters.inputs.utils.SimpleValidator;
import app.adapters.inputs.utils.Utils;
import app.domain.models.Order;
import app.domain.services.InvoiceService;
import app.domain.services.OrderService;
import app.domain.services.SellerService;
import app.domain.services.VeterinaryService;
import app.ports.InputPort;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@Getter
@Setter
@NoArgsConstructor
public class SellerInput implements InputPort{

  @Autowired
  private VeterinaryService veterinaryService;
  @Autowired
  private OrderService orderService;
  @Autowired 
  private SimpleValidator simpleValidator;
  @Autowired
  private SellerService sellerService;
  @Autowired
  private InvoiceService invoiceService;

  private final String MENU = "\nMenu del vendedor, ingrese la opción:" + 
  "\n 1. Consultar orden (ingresando el id de la orden)" +
  "\n 2. Suministrar medicamentos" + 
  "\n 3. Vender medicamentos" +
  "\n 4. Cerrar sesión";

  @Override
  public void menu() throws Exception {
    boolean controlVble = true;
    do {
      controlVble = menu2();
    } while (controlVble);
  }

  private boolean menu2() throws Exception {
		System.out.println(MENU);
		String option = Utils.getReader().nextLine();
		
		switch (option) {
			case "1": {
        this.searchOrder();
				return true;
			}
			case "2": {
        this.supplyMedicines();
				return true;
			}
			case "3": {
        this.sellMedicine();
				return true;
			}
			case "4": {
				return false;
			}
			default: {
				System.out.println("Ha elegido una opción invalida, no sea pendejo :)");
				return true;
			}
		}
	}

  private void sellMedicine() {
    // ToDO
    System.out.println("WIP");
  }

  private void supplyMedicines() throws Exception{
    Order order = searchOrder();

    sellerService.existsMedicine(order);

    // Suministrar = vender, creo, por ende generamos una factura
    System.out.println("\nLos medicamentos asociados a la orden son: ");
    System.out.println(order.getMedicine().getMedicine());

    System.out.println("\nIngrese la cantidad que el usuario va a llevar");
    int count = simpleValidator.intValidator(Utils.getReader().nextLine(), "\"Cantidad \" ");

    System.out.println("Ingrese el valor de los medicamentos");
    double price = simpleValidator.doubleValidator(Utils.getReader().nextLine(), "\"Valor \" ");
    
    Long milisecondsDate = System.currentTimeMillis();
    
    invoiceService.saveInvoice(order, milisecondsDate, count, price);
  }

  private Order searchOrder() throws Exception {
    System.out.println("\nIngrese el id de la orden");
    Long orderId = simpleValidator.longValidator(Utils.getReader().nextLine(), "\"Order Id\" ");

    Order order = orderService.searchOrder(orderId);
    orderService.printOrder(order);

    return order;
  }
}