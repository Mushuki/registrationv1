package com.api.controller;

import com.api.entity.Registration;
import com.api.payload.RegistrationDto;
import com.api.service.RegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//http://localhost:8080/api/v1/registration
@RestController// this will make our api Layer
@RequestMapping("/api/v1/registration") // calling the controller layer
public class RegistrationController {
    private RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {// constructor based dependency injection
        this.registrationService = registrationService;
    }

   /* @GetMapping//java objects are converted into json object
    public List<Registration> getAllRegistrations() {
        List<Registration> registrations = registrationService.getRegistrations();
        return registrations;// here we stored the java objects
         }*/
    @GetMapping
    public ResponseEntity<List<Registration>> getAllRegistrations(){
        List<Registration> registrations = registrationService.getRegistrations();
        return new ResponseEntity<>(registrations, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Registration> createRegistration(
            @RequestBody Registration registration
    ){
        Registration reg = registrationService.createRegistration(registration);
        return new ResponseEntity<>(reg ,HttpStatus.CREATED);//Status code should be 201
    }
    @DeleteMapping
    public ResponseEntity<String> deleteRegistration(
           @RequestParam long id
    ){
     registrationService.deleteRegistration(id);
     return new ResponseEntity<>("Deleted",HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Registration> updateRegistration(@PathVariable long id,
     @RequestBody Registration registration
    ){
        Registration updateRegistration = registrationService.updateRegistration(id,registration);
        return new ResponseEntity<>(updateRegistration,HttpStatus.OK );
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistrationDto> getRegistrationById(

            @PathVariable long id
    ){
       RegistrationDto dto = registrationService.getRegistration(id);
       return new ResponseEntity<>(dto,HttpStatus.OK);
    }


}
