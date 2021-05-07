package com.cdm.extrasbackend.controller;

import com.cdm.extrasbackend.model.Persona;
import com.cdm.extrasbackend.services.PersonaServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin({"*"})
@RestController
@RequestMapping("/api/persona")
public class PersonaRestController {

    @Autowired
    private PersonaServiceAPI personaServiceAPI;

    @GetMapping("/all")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<Persona> getAll(){
        return personaServiceAPI.getAll();
    }

    @GetMapping("/all_active")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<Persona> getAllActive(){
        return personaServiceAPI.findAllPersonas();
    }

    @GetMapping("/find/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public Persona find(@PathVariable String id){
        return personaServiceAPI.get(id);
    }

    @PostMapping("/save")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Persona> save (@RequestBody Persona persona){
        Persona obj = personaServiceAPI.save(persona);
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity <Persona> delete (@PathVariable String id){
        Persona persona = personaServiceAPI.get(id);
        if (persona != null){
            personaServiceAPI.delete(id);
        }else {return  new ResponseEntity<>(persona, HttpStatus.INTERNAL_SERVER_ERROR);}
        return new ResponseEntity<>(persona, HttpStatus.OK);
    }

}
