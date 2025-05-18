package app.adapters.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.domain.models.Login;
import app.domain.services.AdministrationService;
import app.Exceptions.NotFoundException;
import app.Exceptions.BusinessException;
import app.adapters.rest.request.LoginRequest;
import app.domain.services.LoginService;

@RestController
@RequestMapping("/api")
public class LoginController {

	@Autowired
	private AdministrationService administrationService;
	@Autowired
	private LoginService loginService;

    @GetMapping("/users")
    public ResponseEntity<List<Login>> login() {
		try {
			List<Login> users = administrationService.getUsers();

			int i = 1;
			for(Login user : users) {
				System.out.println(i + ". " + user.getUserName());
				i++;
			}
			System.out.println();

			return new ResponseEntity<>(users, HttpStatus.OK);
		}catch (NotFoundException NFe) {
			return new ResponseEntity(NFe.getMessage(), HttpStatus.NOT_FOUND);
		}  catch (BusinessException be) {
			return new ResponseEntity(be.getMessage(), HttpStatus.CONFLICT);
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody LoginRequest request) {
		System.out.println("username: " + request.getUserName());
		System.out.println("password: " + request.getPassword());
		try {
			loginService.login(request.getUserName(), request.getPassword());
			return new ResponseEntity<>("Sucessfully logged in", HttpStatus.OK);
		} catch (NotFoundException NFe) {
			return new ResponseEntity<>(NFe.getMessage(), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

