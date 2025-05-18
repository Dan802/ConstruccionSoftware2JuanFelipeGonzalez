package app.adapters.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.domain.models.Login;
import app.domain.services.AdministrationService;
import app.Exceptions.NotFoundException;
import app.Exceptions.BusinessException;

@RestController
@RequestMapping("/api")
public class LoginController {

	@Autowired
	private AdministrationService administrationService;

    @GetMapping("/login")
    public ResponseEntity<List<Login>> login() {
		try {
			List<Login> users = administrationService.getUsers();

			for(Login user : users) {
				System.out.println(user.getUserName());
			}

			return new ResponseEntity<>(users, HttpStatus.OK);
		}catch (NotFoundException NFe) {
			return new ResponseEntity(NFe.getMessage(), HttpStatus.NOT_FOUND);
		}  catch (BusinessException be) {
			return new ResponseEntity(be.getMessage(), HttpStatus.CONFLICT);
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
}