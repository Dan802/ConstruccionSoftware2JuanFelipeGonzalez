package app.adapters.rest;

import app.Exceptions.BusinessException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.adapters.rest.request.PersonRequest;
import app.domain.models.Login;
import app.domain.services.AdministrationService;
import app.domain.services.LoginService;

@RestController
@RequestMapping("/api")
public class AdminController {

    @Autowired
    private AdministrationService administrationService;

    @Autowired
    private LoginService loginService;

    @PostMapping("/createPerson")
    public ResponseEntity<String> createPerson(@RequestBody PersonRequest request) {
        try {
            // Auth with admin credentials
            Login login = loginService.login(request.getUserNameAdmin(), request.getPasswordAdmin());
            
            if(login.getPersonId().getRole().equals("ADMINISTRADOR")) {
                administrationService.createPerson(request.getDocument(), request.getName(), request.getAge(), request.getUserName(), request.getPassword(), request.getRole().toUpperCase());
                return new ResponseEntity<>("Person created successfully", HttpStatus.CREATED);
            }

            return new ResponseEntity<>("You are not authorized to create a person", HttpStatus.UNAUTHORIZED);
        } catch (BusinessException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
