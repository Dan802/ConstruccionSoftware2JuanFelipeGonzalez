package app.adapters.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.Exceptions.BusinessException;
import app.Exceptions.NotFoundException;
import app.domain.services.InvoiceService;
import app.adapters.rest.request.SellerRequest;
import app.domain.models.Login;
import app.domain.models.Pet;
import app.domain.services.VeterinaryService;
import app.domain.services.LoginService;

@RestController
@RequestMapping("/api")
public class SellerController {

    @Autowired
    private InvoiceService invoiceService;
    @Autowired
    private VeterinaryService veterinaryService;
    @Autowired
    private LoginService loginService;
    
    @PostMapping("/sellMedicine")
    public ResponseEntity<String> sellMedicine(@RequestBody SellerRequest request) {
        System.out.println(request.toString());
        try {
            verifySeller(request.getUserNameSeller(), request.getPasswordSeller());
            Pet pet = veterinaryService.searchPet(request.getPet());

            Long milisecondsDate = System.currentTimeMillis();
            invoiceService.saveInvoice(milisecondsDate, request.getCount(), request.getPrice(), request.getProduct(), pet);

            return new ResponseEntity<>("Medicine sold successfully", HttpStatus.OK);
        } catch (BusinessException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void verifySeller(String userNameSeller, String passwordSeller) throws BusinessException{
        try {
            if(userNameSeller != null && passwordSeller != null) {
                Login login = loginService.login(userNameSeller, passwordSeller);
                if(!login.getPersonId().getRole().equals("VENDEDOR"))
                    throw new BusinessException("Seller not logged in");
            } else {
                throw new BusinessException("Seller not logged in");
            }
        } catch (Exception e) {
            throw new BusinessException("Seller not logged in");
        }
    }
}
