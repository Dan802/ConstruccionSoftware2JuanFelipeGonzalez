package app.adapters.inputs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.adapters.inputs.utils.SimpleValidator;
import app.adapters.inputs.utils.Utils;
import app.adapters.invoice.InvoiceAdapter;
import app.adapters.medicalRecord.MedicalRecordAdapter;
import app.adapters.order.OrderAdapter;
import app.domain.models.Invoice;
import app.domain.models.Order;
import app.domain.models.Person;
import app.domain.models.Pet;
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
  private SimpleValidator simpleValidator;
  @Autowired
  private OrderAdapter orderAdapter;
  @Autowired
  private MedicalRecordAdapter meReAdapter;
  @Autowired
  private InvoiceAdapter invoiceAdapter;

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
    System.out.println("todo triste");
  }

  private void supplyMedicines() throws Exception{

    Order order = searchOrder();

    if(order == null) {
      System.out.println("\nLos vendedores solo pueden suministrar medicamentos mediante una orden.");
      return;
    }
    
    if(order.getMedicalRecordId().getMedicine().length() == 0) {
      System.out.println("\nNo hay medicinas asociadas a la orden suministrada.");
      return;
    }

    // Suministrar = vender, creo, por ende generamos una factura
    System.out.println("\nLos medicamentos asociados a la orden son: ");
    System.out.println(order.getMedicalRecordId().getMedicine());

    System.out.println("\nIngrese la cantidad que el usuario va a llevar");
    int count = simpleValidator.intValidator(Utils.getReader().nextLine(), "\"Cantidad \" ");

    System.out.println("Ingrese el valor de los medicamentos");
    double price = simpleValidator.doubleValidator(Utils.getReader().nextLine(), "\"Valor \" ");
    
    Long milisecondsDate = System.currentTimeMillis();

    Invoice invoice = new Invoice();
    invoice.setCount(count);
    invoice.setDateCreated(milisecondsDate);
    invoice.setOrderId(order);
    invoice.setPetId(order.getPetId());
    invoice.setPrice(price);
    invoice.setProductName(order.getMedicalRecordId().getMedicine());
    
    Invoice savedInvoice = invoiceAdapter.save(invoice);

    System.out.println("\nSu factura es: ");
    System.out.println("\n1. Id de la factura: " + savedInvoice.getInvoiceId());  
    System.out.println("2. Mascota: " + savedInvoice.getPetId().getName());  
    System.out.println("3. Dueño: " + savedInvoice.getOwnerId().getName());  
    System.out.println("4. Id de la orden: " + savedInvoice.getOrderId().getOrderId());  
    System.out.println("5. Nombre del medicamento: " + savedInvoice.getProductName());  
    System.out.println("6. Precio: " + savedInvoice.getPrice());  
    System.out.println("7. Cantidad: " + savedInvoice.getCount());  
    System.out.println("8. Fecha de creación: " + savedInvoice.getDateCreated());  
  }

  private Order searchOrder() throws Exception {
    System.out.println("\nIngrese el id de la orden");
    Long orderId = simpleValidator.longValidator(Utils.getReader().nextLine(), "\"Order Id\" ");
    Order order = orderAdapter.findByOrderId(orderId);

    if(order == null) {
      System.out.println("\nNo se encontró la orden.");
      return null;
    }
    veterinaryService.printOrder(order);
    return order;
  }
}