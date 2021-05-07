package com.cdm.extrasbackend.controller;

import com.cdm.extrasbackend.model.Proyecto;
import com.cdm.extrasbackend.services.ProyectoServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin({"*"})
@RestController
@RequestMapping("/api/proyecto")
public class ProyectoRestController {

    @Autowired
    private ProyectoServiceAPI proyectoServiceAPI;

    @GetMapping("/all")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<Proyecto> getAll(){
        return proyectoServiceAPI.getAll();
    }

    @GetMapping("/find/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public Proyecto find(@PathVariable String id){
        return proyectoServiceAPI.get(id);
    }

    @PostMapping("/save")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Proyecto> save (@RequestBody Proyecto proyecto){
        Proyecto obj = proyectoServiceAPI.save(proyecto);
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity <Proyecto> delete (@PathVariable String id){
        Proyecto proyecto = proyectoServiceAPI.get(id);
        if (proyecto != null){
            proyectoServiceAPI.delete(id);
        }else {return  new ResponseEntity<>(proyecto, HttpStatus.INTERNAL_SERVER_ERROR);}
        return new ResponseEntity<>(proyecto, HttpStatus.OK);
    }

}
