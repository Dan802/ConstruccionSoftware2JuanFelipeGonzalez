package app.adapters.rest;

import java.util.ArrayList;
import java.util.List;

import app.Exceptions.BusinessException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.adapters.rest.request.PersonRequest;
import app.domain.models.Person;
import app.domain.services.AdministrationService;

@RestController
@RequestMapping("/api")
public class AdminController {

    @Autowired
    private AdministrationService administrationService;

    @PostMapping("/createPerson")
    public ResponseEntity<String> createPerson(@RequestBody PersonRequest request) {
        try {
            System.out.println(request.toString());

            administrationService.createPerson(request.getDocument(), request.getName(), request.getAge(), request.getUserName(), request.getPassword(), request.getRole());

            return new ResponseEntity<>("Person created successfully", HttpStatus.CREATED);
        } catch (BusinessException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
