package app.adapters.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import app.Exceptions.BusinessException;
import app.adapters.rest.request.PetOwnerRequest;
import app.domain.services.VeterinaryService;

@RestController
@RequestMapping("/api")
public class VetController {

    @Autowired
    private VeterinaryService veterinaryService;

    @PostMapping("/createPetOwner")
    public ResponseEntity<String> createPetOwner(@RequestBody PetOwnerRequest request) {
        System.out.println(request.toString());
        try {
            veterinaryService.savePetOwner(request.getDocument(), request.getName(), request.getAge());
            return new ResponseEntity<>("Person created successfully", HttpStatus.CREATED);
        } catch (BusinessException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
