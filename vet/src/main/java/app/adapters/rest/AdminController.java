package app.adapters.rest;

import java.util.ArrayList;
import java.util.List;

import app.Exceptions.BusinessException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.domain.models.Person;
import app.domain.services.AdministrationService;

@RestController
@RequestMapping("/api")
public class AdminController {

    @Autowired
    private AdministrationService administrationService;
}
